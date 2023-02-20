package ps.lenovehp.pharmacy

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import ps.lenovehp.pharmacy.adapter.ApapterCategorySpinner
import ps.lenovehp.pharmacy.adapter.ConstModel
import ps.lenovehp.pharmacy.adapter.DatabaseHelper
import ps.lenovehp.pharmacy.adapter.ItemModel
import ps.lenovehp.pharmacy.databinding.FragmentNewItemBinding
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewmedFrag : Fragment() {

    private var _binding: FragmentNewItemBinding? = null
    lateinit var  mydb : DatabaseHelper
    var cal = Calendar.getInstance()
    private val binding get() = _binding!!


    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewItemBinding.inflate(inflater, container, false)
        mydb = DatabaseHelper(context)

        var Mlist: List<ConstModel> =  mydb.GetCategory()

        binding.category.adapter = context?.let { ApapterCategorySpinner(it,Mlist) }

        var  ItemId: Int = requireArguments().getInt("ItemId")



        var Mlist2:List<ConstModel> =  mydb.GetCompanies()



        binding.company.adapter = context?.let { ApapterCategorySpinner(it,Mlist2) }



        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInViewDate(1)
            }
        }
        val dateSetListener2 = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInViewDate(2)
            }
        }


        binding.productionDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(context!!,
                    dateSetListener,

                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        binding.expirationdate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(context!!,
                    dateSetListener2,

                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

binding.btnDelete.setOnClickListener()
{
    if (binding.itemID.text.toString().equals(""))
    {
        Toast.makeText(context ,"Enter Item No" , Toast.LENGTH_SHORT).show()
        return@setOnClickListener
    }


    val builder = AlertDialog.Builder(context)
    builder.setTitle("Are you sure Delete Item")

    builder.setPositiveButton("Ok",
        DialogInterface.OnClickListener {
                dialog,
                which ->
            DeleteItem() })

    builder.setNegativeButton("No",
    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })

    val alert = builder.create()


    alert.show()

}

        binding.btnSave.setOnClickListener()
        {

            var mcategory = binding.category.selectedItem as ConstModel
            var mcompany = binding.company.selectedItem as ConstModel


            var id :Int
            if (binding.itemID.text.toString()!="")
                id = binding.itemID.text.toString().toInt()
            else
                id =0
            var model = ItemModel(id,binding.generalName.text.toString()
            , binding.tradename.text.toString() ,
                mcompany.Id,
                mcategory.Id,
                binding.productionDate.text.toString(),
                binding.expirationdate.text.toString(),
                binding.price.text.toString().toFloat(),
                binding.descreption.text.toString(),
                binding.unitsnumber.text.toString().toFloat() ,
                binding.shelf.text.toString(),
               null

            )

           var idItem = mydb.saveItem(model)
            binding.itemID.setText(idItem.toString())

            Toast.makeText(context , "Save Sucessed Item Number : " + idItem.toString(), Toast.LENGTH_LONG).show()
        }



        binding.btnLoadImg.setOnClickListener()
        {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)

        }
        binding.btnClose.setOnClickListener()
        {



            getActivity()?.onBackPressed()
        }


        if (ItemId !=0)
        {
            val model = mydb.getItem(ItemId)

            binding.itemID.setText(model.ID.toString())
            binding.generalName.setText(model.generalName.toString())
            binding.tradename.setText(model.tradename.toString())



        var i: Int =0
            mydb.GetCategory().forEach {
                if (it.Id== model.category) {
                    binding.category.setSelection(i)
                    return@forEach
                }
                i++
            }


            var j: Int = 0

            mydb.GetCompanies().forEach {
                if (it.Id== model.company) {
                    binding.company.setSelection(j)
                    return@forEach
                }
                j++
            }
            binding.productionDate.setText(model.productionDate.toString())
            binding.expirationdate.setText(model.expirationdate.toString())
            binding.price.setText(model.price.toString())
            binding.descreption.setText(model.description)
            binding.unitsnumber.setText(model.unitsnumber.toString())
            binding.shelf.setText(model.shelf.toString())


        }
        return binding.root

    }


    fun DeleteItem()
    {
        mydb.itemDelete(binding.itemID.text.toString().toLong())
        Toast.makeText(context, "Item Is Delete ", Toast.LENGTH_SHORT).show()
        getActivity()?.onBackPressed()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun  updateDateInViewDate(id :Int)
    {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        if (id ==1)
        binding.productionDate.setText(sdf.format(cal.getTime()))
        else
            binding.expirationdate.setText(sdf.format(cal.getTime()))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.img.setImageURI(imageUri)
        }
    }




}