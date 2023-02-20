package ps.lenovehp.pharmacy.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.R
import ps.lenovehp.pharmacy.group.FragmentDialogConst
import java.util.ArrayList
import androidx.fragment.app.FragmentActivity




class AdapterConst  : RecyclerView.Adapter<AdapterConst.ConstHolder>() {
    private var Mlist: ArrayList<ConstModel> = ArrayList()

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_const, parent, false)



        return ConstHolder(itemview)
    }

    override fun onBindViewHolder(holder: ConstHolder, position: Int) {
        val model = Mlist[position]
        try {
            holder.item_name.setText(model.const_name.toString())

            holder.id.text = model.Id.toString()
            holder.descreption.text= model.descrption

            holder.types = model.types

            holder.btn_delete.setOnClickListener()
            {

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Are you sure Delete Item")

                builder.setPositiveButton("Ok",
                    DialogInterface.OnClickListener {
                            dialog,
                            which ->



                        var mydb :DatabaseHelper = DatabaseHelper(context)


                        mydb.deleteCategory( holder.id.text.toString().toInt())
                        Mlist.removeAt(holder.adapterPosition)
                        notifyDataSetChanged()

                    })

                builder.setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })

                val alert = builder.create()


                alert.show()



            }



            holder.btn_edit.setOnClickListener()
            {
                val bundle = Bundle()



                bundle.putInt("ConstType", model.types)

                bundle.putInt("ConstId", holder.id.text.toString().toInt())
                var fragement = FragmentDialogConst()

                fragement.setArguments(bundle)

                (context as FragmentActivity?)?.let { it1 -> fragement.show(it1.supportFragmentManager, "customfragment") }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return Mlist.size
    }

    fun setItems(models: ArrayList<ConstModel>?, _context: Context) {
        if (models != null) {
            Mlist = models
        }

        context = _context

        notifyDataSetChanged()
    }


    public class ConstHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_name: TextView

        val descreption : TextView
        var types : Int
        var id: TextView
        var btn_edit: ImageView
        var btn_delete: ImageView

        init {
            item_name = itemView.findViewById(R.id.item_name)
            descreption = itemView.findViewById(R.id.descreption)
            id = itemView.findViewById(R.id.serial_no)
            btn_edit = itemView.findViewById(R.id.btn_edit)
            btn_delete = itemView.findViewById(R.id.btn_delete)
            types=1
        }
    }
}
