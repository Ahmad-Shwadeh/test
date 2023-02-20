package ps.lenovehp.pharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.*
import ps.lenovehp.pharmacy.databinding.FragmentItemBalanceBinding

class medbalfrag : Fragment() {
    lateinit var  mydb : DatabaseHelper

    private var _binding: FragmentItemBalanceBinding? = null
    lateinit var adapter: AdapterItemBalance

    private val binding get() = _binding!!
    var  types : Int =0
    lateinit var Mlist:ArrayList<ItemBalanceModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentItemBalanceBinding.inflate(inflater, container, false)



        mydb  = DatabaseHelper(context)
        var Mlist:ArrayList<ItemBalanceModel>
        Mlist = mydb.GetItemsBalance()



        var listItems: RecyclerView
        listItems = binding.ListItem
        listItems.layoutManager = LinearLayoutManager(context)
        listItems.setHasFixedSize(true)
        listItems!!.isNestedScrollingEnabled = true
        adapter = AdapterItemBalance()

        listItems.adapter = adapter
        context?.let { adapter.setItems(Mlist, it) }

        return binding.root
    }


}