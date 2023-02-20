package ps.lenovehp.pharmacy.group.codes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.DataActivity
import ps.lenovehp.pharmacy.group.account.UserInformation
import ps.lenovehp.pharmacy.R
import java.util.ArrayList

public class Adapter_sub_menu : RecyclerView.Adapter<Adapter_sub_menu.ListHolder>() {
    private var Mlist: ArrayList<Model_Heder> = ArrayList()

    lateinit var context : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_menus, parent, false)
        return  ListHolder(itemview)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val model = Mlist[position]
        try {
            holder.item_name.setText(model.descreption.toString())

            holder.id= model.id

            holder.frame.setOnClickListener()
            {

                when (holder.id)
                {
                     1->
                     {

                         val bundle = Bundle()
                         bundle.putInt("ActivityType", holder.id)
                         bundle.putInt("ItemId", 0)

                         val intent = Intent(context, DataActivity::class.java)
                         intent.putExtras(bundle)
                         context.startActivity(intent)
                     }
                    5->
                    {

                        val bundle = Bundle()
                        bundle.putInt("ActivityType", holder.id)
                        bundle.putInt("Category_no", 0)
                        val intent = Intent(context, DataActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    10->
                    {

                        val bundle = Bundle()
                        bundle.putInt("ActivityType", holder.id)
                        val intent = Intent(context, DataActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    50 ->
                    {
                        val intent = Intent(context,UserInformation::class.java)
                        context.startActivity(intent)
                    }
                    55->
                    {

                        val bundle = Bundle()
                        bundle.putInt("ActivityType", holder.id)
                        val intent = Intent(context, DataActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    60->
                    {

                        val bundle = Bundle()
                        bundle.putInt("ActivityType", holder.id)
                        val intent = Intent(context, DataActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }

                    100->
                    {

                        val bundle = Bundle()
                        bundle.putInt("ActivityType", holder.id)
                        val intent = Intent(context, DataActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }


                    105->
                    {

                        val bundle = Bundle()
                        bundle.putInt("ActivityType", holder.id)
                        val intent = Intent(context, DataActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    110->
                    {

                        val bundle = Bundle()
                        bundle.putInt("ActivityType", holder.id)
                        val intent = Intent(context, DataActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }

                }


            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return Mlist.size
    }

    fun setItems(models: ArrayList<Model_Heder>? , _context : Context) {
        if (models != null) {
            Mlist = models
        }

        context = _context

        notifyDataSetChanged()
    }


    public class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_name:TextView
        val frame : LinearLayout
        var id: Int = 0

        init {
            item_name =itemView.findViewById(R.id.item_name)
            frame = itemView.findViewById(R.id.frame)


        }
    }



}