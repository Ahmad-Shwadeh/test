package ps.lenovehp.pharmacy.group.ash
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.ItemModel
import ps.lenovehp.pharmacy.R
import java.util.ArrayList
import android.app.Activity
import android.app.Activity.RESULT_OK


class AdaptermedSearch : RecyclerView.Adapter<AdaptermedSearch.ItemsHolder>() {
    private var Mlist: ArrayList<ItemModel> = ArrayList()

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_seach, parent, false)



        return ItemsHolder(itemview)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val model = Mlist[position]
        try {
            holder.item_name.setText(model.generalName.toString())

            holder.id.text = model.ID.toString()

            holder.panel.setOnLongClickListener()
            {

                val intent = Intent()
                intent.putExtra("ID", holder.id.text.toString())
                intent.putExtra("Name", holder.item_name.text.toString())


                val _con: Context = holder.item_name.getContext()

                (_con as Activity).setResult(RESULT_OK, intent)



                _con.finish()




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


        val item_name: TextView

        var id: TextView
        var panel: LinearLayout

        init {
            item_name = itemView.findViewById(R.id.lbl_name)

            id = itemView.findViewById(R.id.lbl_id)
            panel = itemView.findViewById(R.id.panel_item)

        }
    }
}