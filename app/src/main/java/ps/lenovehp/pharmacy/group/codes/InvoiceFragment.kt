package ps.lenovehp.pharmacy.group.codes

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import ps.lenovehp.pharmacy.medsearch

import ps.lenovehp.pharmacy.databinding.FragmentInvoiceBinding



import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.DatabaseHelper
import ps.lenovehp.pharmacy.adapter.InvoiceItemModel
import ps.lenovehp.pharmacy.adapter.InvoiceModel
import ps.lenovehp.pharmacy.group.ash.Adaptervoice
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class InvoiceFragment : Fragment() {

    private var _binding: FragmentInvoiceBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: Adaptervoice
    lateinit var  mydb : DatabaseHelper
    lateinit var Mlist:ArrayList<InvoiceItemModel>

     lateinit var TotalAmount :TextView
    lateinit var ItemCount :TextView

    var cal = Calendar.getInstance()

    var inv_type :Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK)
        {


            var item_id =data?.getStringExtra("ID")!!.toInt()
          var model =   mydb.getItem(item_id)

            var price = model.price
            var amount = model.price


             ItemCount = binding.itemCount


            TotalAmount = binding.Gtotal

            var modelitem = InvoiceItemModel(0,0, data?.getStringExtra("ID")!!.toInt() , data?.getStringExtra("Name").toString() ,1.0F,price,amount,inv_type)
            Mlist.add(modelitem)
            context?.let { adapter.setItems(Mlist, it,TotalAmount,ItemCount) }


        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInvoiceBinding.inflate(inflater, container, false)
        val root: View = binding.root
        inv_type= requireArguments().getInt("inv_type")
        if (inv_type==1)
            getActivity()?.setTitle("Sales Invoice")
        else
            getActivity()?.setTitle("Purchases Invoice")




        binding.btnItemList.setOnClickListener()
        {
            val intent = Intent(context, medsearch::class.java)
            startActivityForResult(intent,1)
        }

         Mlist=ArrayList<InvoiceItemModel>()

        mydb  = DatabaseHelper(context)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        try {



        var listItems: RecyclerView

        listItems =binding.recyclerItemSales

        listItems.layoutManager = LinearLayoutManager(context)
        listItems.setHasFixedSize(true)
        listItems!!.isNestedScrollingEnabled = true

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = sdf.format(Date())
            binding.txtdate.setText(currentDate)


       adapter = Adaptervoice()
            listItems.adapter = adapter

            TotalAmount = binding.Gtotal
            ItemCount = binding.itemCount

          //  Toast.makeText(context, Mlist.size.toString(), Toast.LENGTH_LONG).show()
         adapter.setItems(Mlist, root.context, TotalAmount,ItemCount)
        binding.btnnew.setOnClickListener()
        {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = sdf.format(Date())
            binding.txtdate.setText(currentDate)

            binding.guestName.setText("Sales Cash")
            Mlist.clear()
            adapter.setItems(Mlist, root.context, TotalAmount,ItemCount)
        }


            binding.btnsave.setOnClickListener()
            {
            if (binding.txtdate.text.toString()=="")
            {
                Toast.makeText(context, "Enter the Date:" , Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

                if (binding.guestName.text.toString() =="")
                {
                    Toast.makeText(context, "Enter the Guest Name:" , Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                if (Mlist.size==0)
                {
                    Toast.makeText(context, "Enter the Item List:" , Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                var inv = InvoiceModel(0,binding.txtdate.text.toString(), binding.guestName.text.toString() ,binding.Gtotal.text.toString().toFloat() ,inv_type )
                var id = mydb.SaveInvoice(inv,Mlist)
                binding.invNo.setText(id.toString())

               Toast.makeText(context ,"Save Invoice", Toast.LENGTH_LONG ).show()
            }

            binding.btnDelete.setOnClickListener()
            {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Are you sure Delete Item")

                builder.setPositiveButton("Ok",
                    DialogInterface.OnClickListener {
                            dialog,
                            which ->


                        if (binding.invNo.text.toString()=="")
                        {
                            Toast.makeText(context, "Enter the  Invoice ID", Toast.LENGTH_LONG).show()
                            return@OnClickListener
                        }

                        mydb.DeleteInvoice(binding.invNo.text.toString().toInt() , inv_type)

                        Toast.makeText(context, "Invoice Is Deleting", Toast.LENGTH_LONG).show()


                        val sdf = SimpleDateFormat("dd/MM/yyyy")
                        val currentDate = sdf.format(Date())
                        binding.txtdate.setText(currentDate)

                        binding.guestName.setText("Sales Cash")
                        Mlist.clear()
                        adapter.setItems(Mlist, root.context, TotalAmount,ItemCount)


                    })

                builder.setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })

                val alert = builder.create()


                alert.show()




            }


         binding.txtdate!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    DatePickerDialog(context!!,
                        dateSetListener,

                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }

            })
            binding.lbldate!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    DatePickerDialog(context!!,
                        dateSetListener,

                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }

            })

        }
        catch (e:Exception)
        {
            Toast.makeText(context , e.message.toString(), Toast.LENGTH_LONG).show()
        }

        return root

       // return inflater.inflate(R.layout.fragment_invoice, container, false)
    }





fun  updateDateInView()
{
    val myFormat = "dd/MM/yyyy" // mention the format you need
    val sdf = SimpleDateFormat(myFormat, Locale.US)
    binding.txtdate.setText(sdf.format(cal.getTime()))
}



}