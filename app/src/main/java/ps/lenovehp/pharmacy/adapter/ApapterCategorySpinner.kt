package ps.lenovehp.pharmacy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ps.lenovehp.pharmacy.R


class ApapterCategorySpinner (ctx: Context,
                              Mlist: List<ConstModel>) :
    ArrayAdapter<ConstModel>(ctx, 0, Mlist) {


    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)

    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {

        val model = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.row_spiner,
            parent,
            false
        )

        var txt = view.findViewById<TextView>(R.id.Txtspiner)
        if (model != null) {
            txt.text= model.const_name.toString()
            txt.setTag(model.Id.toString())
        }

        return view
    }
}


