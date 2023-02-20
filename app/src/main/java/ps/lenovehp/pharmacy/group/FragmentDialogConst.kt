package ps.lenovehp.pharmacy.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import ps.lenovehp.pharmacy.adapter.ConstModel
import ps.lenovehp.pharmacy.adapter.DatabaseHelper
import ps.lenovehp.pharmacy.R

class FragmentDialogConst : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootview: View = inflater.inflate(R.layout.row_const, container , false)

        val constName = rootview.findViewById<EditText>(R.id.constName)
        val Constdescreption = rootview.findViewById<EditText>(R.id.Constdescreption)
        val constID = rootview.findViewById<EditText>(R.id.constID)

        val btnsave = rootview.findViewById<Button>(R.id.btnsave)
        val btncancel = rootview.findViewById<Button>(R.id.btncancel)

        var  mydb : DatabaseHelper = DatabaseHelper(context)


        val types: Int? = getArguments()?.getInt("ConstType")
        val Const_Id: Int =  requireArguments().getInt("ConstId")

        //var myconst = types?.let { mydb.GetConst(constID.text.toString().toInt(), it) }

            if (Const_Id !=0) {
                var myconst = mydb.GetConst(Const_Id, types)

                if (myconst != null) {
                    constName.setText(myconst.const_name.toString())
                    Constdescreption.setText(myconst.descrption.toString())
                    constID.setText(myconst.Id.toString())
                }

            }


        btnsave.setOnClickListener {

            try {


                if (constName.text.toString().equals("")) {
                    Toast.makeText(context, "Enter Name", Toast.LENGTH_SHORT).show()

                    return@setOnClickListener

                }



                var model =ConstModel (Const_Id
                    ,constName.text.toString(),
                    Constdescreption.text.toString(),types!!)



                try {
                    var mydb:DatabaseHelper= DatabaseHelper(context)
                    if (types==60)
                        mydb.Companies_Save(model)
                    else
                        mydb.Category_Save(model)



                } catch (e: Exception) {
                }


                Toast.makeText(context, "Save Sucess", Toast.LENGTH_SHORT).show()
                dismiss()

            }
            catch (e:Exception)
            {
                Toast.makeText(context , e.message.toString(), Toast.LENGTH_LONG).show()
            }

        }


        btncancel.setOnClickListener {
            dismiss()
        }




        return  rootview
    }
}