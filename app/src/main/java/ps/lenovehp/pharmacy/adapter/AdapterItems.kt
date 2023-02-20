package ps.lenovehp.pharmacy.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.DataActivity
import ps.lenovehp.pharmacy.R
import java.util.ArrayList

class AdapterItems  : RecyclerView.Adapter<AdapterItems.ItemsHolder>() {
    private var Mlist: ArrayList<ItemModel> = ArrayList()

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_list, parent, false)



        return ItemsHolder(itemview)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val model = Mlist[position]
        try {
            holder.generalName.setText(model.generalName.toString())

            holder.id.text = model.ID.toString()
            holder.expirationdate.text = model.expirationdate.toString()
            holder.price.text = model.price.toString()

            holder.panel.setOnLongClickListener()
            {
                val bundle = Bundle()
                bundle.putInt("ActivityType",1)
                bundle.putInt("ItemId",holder.id.text.toString().toInt())
                val intent = Intent(context, DataActivity::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)

                Toast.makeText(context, holder.id.text.toString(), Toast.LENGTH_LONG).show()
                return@setOnLongClickListener true
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return Mlist.size
    }

    fun setItems(models: ArrayList<ItemModel>?, _context: Context) {
        if (models != null) {
            Mlist = models
        }

        context = _context

        notifyDataSetChanged()
    }


    public class ItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val generalName: TextView
        val expirationdate: TextView
        val price: TextView
        var id: TextView
        var panel : LinearLayout
      //  var btn_edit: Button
        //var btn_delete: Button

        init {
            generalName = itemView.findViewById(R.id.generalName)
            expirationdate = itemView.findViewById(R.id.expirationdate)
            price = itemView.findViewById(R.id.price)
            id = itemView.findViewById(R.id.serial_no)
            panel = itemView.findViewById(R.id.panel)
          //  btn_edit = itemView.findViewById(R.id.btn_edit)
           // btn_delete = itemView.findViewById(R.id.btn_delete)

        }
    }
}