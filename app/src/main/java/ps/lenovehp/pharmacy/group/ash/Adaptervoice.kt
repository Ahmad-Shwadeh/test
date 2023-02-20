package ps.lenovehp.pharmacy.group.ash

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.InvoiceItemModel
import ps.lenovehp.pharmacy.R
import java.lang.String
import java.util.ArrayList

class Adaptervoice  : RecyclerView.Adapter<Adaptervoice.ItemsHolder>() {
    private var Mlist: ArrayList<InvoiceItemModel> = ArrayList()

    lateinit var context: Context
    lateinit var GtotalAmount : TextView
    lateinit var ItemCount : TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_invoice_item, parent, false)



        return ItemsHolder(itemview)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val model = Mlist[position]
        try {
           holder.item_name.setText(model.item_name)

           holder.txtqty.setText(model.qty.toString())
           holder.txtprice.setText(model.price.toString())
            holder.txtamount.text = model.amount.toString()

            // holder.id.text = model.ID.toString()


            holder.btn_delete.setOnClickListener()
            {

                    Mlist.removeAt(holder.getPosition());
                    notifyItemRemoved(holder.getPosition());
                    notifyDataSetChanged();


            }

            holder.btn_qty_add.setOnClickListener()
            {
                try {


                    Mlist.get(holder.getPosition()).qty = Mlist.get(holder.getPosition()).qty + 1

                    val _amount: Float =
                        Mlist.get(holder.getPosition()).qty * Mlist.get(holder.getPosition()).price
                    Mlist.get(holder.getPosition()).amount = _amount
                    holder.txtqty.setText(String.valueOf(Mlist.get(holder.getPosition()).qty))
                    holder.txtamount.setText(String.valueOf(Mlist.get(holder.getPosition()).amount))

                    notifyDataSetChanged()
                }
                catch (e:java.lang.Exception)
                {
                    Toast.makeText(context,e.message.toString(), Toast.LENGTH_LONG).show()
                }

            }
            holder.btn_qty_subtract.setOnClickListener()
            {

                try {


                    Mlist.get(holder.getPosition()).qty = Mlist.get(holder.getPosition()).qty - 1;

                    if (Mlist.get(holder.getPosition()).qty <= 1) Mlist.get(holder.getPosition()).qty =
                        (1).toFloat();

                    Mlist.get(holder.getPosition()).amount =
                        Mlist.get(holder.getPosition()).qty * Mlist.get(holder.getPosition()).price;


                    holder.txtqty.setText(String.valueOf(Mlist.get(holder.getPosition()).qty));
                    holder.txtamount.setText(String.valueOf(Mlist.get(holder.getPosition()).amount));
                    //notifyItemChanged(vh.getPosition());

                    notifyDataSetChanged();
                }
                catch (e:java.lang.Exception)
                {
                    Toast.makeText(context,e.message.toString(), Toast.LENGTH_LONG).show()
                }
            }


            holder.txtqty.addTextChangedListener(object  : TextWatcher


            {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    try {



                        if (holder.txtqty.getText().toString().equals("")) {
                          holder.txtqty.setText("1")

                            Mlist.get(holder.getPosition()).qty = (1).toFloat()
                       }

                        Mlist.get(holder.getPosition()).qty =
                            java.lang.Float.valueOf(holder.txtqty.getText().toString())
                        if (Mlist.get(holder.getPosition()).qty <= 0) Mlist.get(holder.getPosition()).qty =
                            (1).toFloat()
                        val _amount: Float =
                            Mlist.get(holder.getPosition()).qty * Mlist.get(holder.getPosition()).price
                        Mlist.get(holder.getPosition()).amount = _amount

                        holder.txtamount.setText(String.valueOf(Mlist.get(holder.getPosition()).amount))
                        
                        GtotalAmount.text = getTotalAmount().toString()

                        ItemCount.text = Mlist.size.toString()
                    } catch (ex: java.lang.Exception) {
                        Toast.makeText(context,ex.message.toString(), Toast.LENGTH_LONG).show()
                    }

                }

            }
            )

            holder.txtprice.addTextChangedListener(object  : TextWatcher
            {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {

                    try {
                        if (holder.txtprice.getText().toString().equals("")) {
                            holder.txtprice.setText("0")
                            Mlist.get(holder.getPosition()).price = (0).toFloat()
                        }
                        Mlist.get(holder.getPosition()).price =
                            java.lang.Float.valueOf(holder.txtprice.getText().toString())


                        Mlist.get(holder.getPosition()).amount =
                            Mlist.get(holder.getPosition()).qty * Mlist.get(holder.getPosition()).price
                        holder.txtamount.setText(String.valueOf(Mlist.get(holder.getPosition()).amount))

                       GtotalAmount.text = getTotalAmount().toString()

                        ItemCount.text = Mlist.size.toString()
                    } catch (ex: java.lang.Exception) {

                        Toast.makeText(context,ex.message.toString(), Toast.LENGTH_LONG).show()
                    }


                }

            }
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getTotalAmount() : Float
    {
        var amt : Float =0F
        for (m in Mlist)
        {
            amt = amt + m.amount
        }

        return  amt

    }

    override fun getItemCount(): Int {
        return Mlist.size
    }

    fun setItems(models: ArrayList<InvoiceItemModel>?, _context: Context , _gtotal : TextView , _ItemCount : TextView) {
        GtotalAmount = _gtotal
        ItemCount =_ItemCount
        if (models != null) {
            Mlist = models

            GtotalAmount.text = getTotalAmount().toString()

            ItemCount.text = Mlist.size.toString()
        }

        context = _context

        notifyDataSetChanged()
    }


    public class ItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


            val item_name: TextView
            val btn_delete : ImageView
            val btn_qty_add :Button
            val txtqty : EditText
            val btn_qty_subtract : Button
            val  txtprice : EditText

            val txtamount : TextView


        init {
            item_name = itemView.findViewById(R.id.item_name)
            btn_delete = itemView.findViewById(R.id.btn_delete)
            btn_qty_add = itemView.findViewById(R.id.btn_qty_add)
            txtqty = itemView.findViewById(R.id.txtqty)
            btn_qty_subtract = itemView.findViewById(R.id.btn_qty_subtract)
            txtprice = itemView.findViewById(R.id.txtprice)
            txtamount = itemView.findViewById(R.id.txtamount)



        }

    }
}