package ps.lenovehp.pharmacy.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.DataActivity
import ps.lenovehp.pharmacy.R
import java.util.ArrayList

class AdapterCategory : RecyclerView.Adapter<AdapterCategory.ConstHolder>() {
    private var Mlist: ArrayList<ConstModel> = ArrayList()

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_category, parent, false)



        return ConstHolder(itemview)
    }

    override fun onBindViewHolder(holder: ConstHolder, position: Int) {
        val model = Mlist[position]
        try {
            holder.btnCategory.setText(model.const_name.toString())

            holder.btnCategory.setTag(model.Id.toString())

              holder.btnCategory.setOnClickListener()
             {
                 val bundle = Bundle()
                 bundle.putInt("ActivityType", 5)
                 bundle.putInt("Category_no", holder.btnCategory.getTag().toString().toInt())
                 val intent = Intent(context, DataActivity::class.java)
                 intent.putExtras(bundle)
                 context.startActivity(intent)

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
        val btnCategory: Button

        init {
            btnCategory = itemView.findViewById(R.id.btnCategory)
        }
    }
}