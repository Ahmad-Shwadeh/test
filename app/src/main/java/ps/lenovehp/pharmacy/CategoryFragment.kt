package ps.lenovehp.pharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.*
import ps.lenovehp.pharmacy.databinding.FragmentCategoryBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    lateinit var adapter: AdapterCategory
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)


        var  mydb : DatabaseHelper = DatabaseHelper(context)


        var Mlist:ArrayList<ConstModel>



        Mlist = mydb.GetCategory()


        var listItems: RecyclerView
        listItems = binding.ListItem
        listItems.layoutManager = GridLayoutManager(context,3)
        listItems.setHasFixedSize(true)
        listItems!!.isNestedScrollingEnabled = true


        adapter = AdapterCategory()

        listItems.adapter = adapter
        context?.let { adapter.setItems(Mlist, it) }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}