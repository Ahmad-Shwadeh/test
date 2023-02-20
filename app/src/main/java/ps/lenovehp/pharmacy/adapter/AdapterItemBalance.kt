package ps.lenovehp.pharmacy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.R
import java.util.ArrayList

class AdapterItemBalance : RecyclerView.Adapter<AdapterItemBalance.ItemsHolder>() {
    private var Mlist: ArrayList<ItemBalanceModel> = ArrayList()

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_balance, parent, false)



        return ItemsHolder(itemview)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val model = Mlist[position]
        try {
            holder.itemID.setText(model.id.toString())

            holder.item_name.setText( model.item_name)
            holder.qty_stb.setText(model.qty_start_balance.toString())
            holder.qty_perchaues.setText( model.qty_puerchaes.toString())
            holder.qty_sales.setText(model.qty_sales.toString())
            holder.qty_balance.setText(model.qty_balance.toString())
            

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return Mlist.size
    }

    fun setItems(models: ArrayList<ItemBalanceModel>?, _context: Context) {
        if (models != null) {
            Mlist = models
        }

        context = _context

        notifyDataSetChanged()
    }


    public class ItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val itemID: TextView
        val item_name: TextView
        val qty_stb: TextView
        var qty_perchaues: TextView


        var qty_sales: TextView

        var qty_balance: TextView

        init {
            itemID = itemView.findViewById(R.id.id)
            item_name = itemView.findViewById(R.id.item_name)
            qty_stb = itemView.findViewById(R.id.qty_stb)
            qty_perchaues = itemView.findViewById(R.id.qty_perchaues)
            qty_sales = itemView.findViewById(R.id.qty_sales)
            qty_balance = itemView.findViewById(R.id.qty_balance)

        }
    }
}