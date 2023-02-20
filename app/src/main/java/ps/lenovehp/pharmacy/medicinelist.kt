package ps.lenovehp.pharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.*
import ps.lenovehp.pharmacy.databinding.FragmentItemListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class medicinelist : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    lateinit var adapter: AdapterItems
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var  mydb : DatabaseHelper
    var  category_no:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)



          mydb  = DatabaseHelper(context)


        var Mlist:ArrayList<ItemModel>



          category_no = requireArguments().getInt("Category_no")

            Mlist = mydb.GetItems(category_no)


        var listItems: RecyclerView
        listItems = binding.ListItem
        listItems.layoutManager = LinearLayoutManager(context)
        listItems.setHasFixedSize(true)
        listItems!!.isNestedScrollingEnabled = true


        adapter = AdapterItems()

        listItems.adapter = adapter
        context?.let { adapter.setItems(Mlist, it) }




        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        var Mlist:ArrayList<ItemModel>
        Mlist = mydb.GetItems(category_no)
        context?.let { adapter.setItems(Mlist, it) }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}