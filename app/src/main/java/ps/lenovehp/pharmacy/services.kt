package ps.lenovehp.pharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.AdapterConst
import ps.lenovehp.pharmacy.adapter.ConstModel
import ps.lenovehp.pharmacy.adapter.DatabaseHelper
import ps.lenovehp.pharmacy.databinding.FragmentServicesConstantBinding
import ps.lenovehp.pharmacy.group.FragmentDialogConst

class services : Fragment() {
    lateinit var  mydb : DatabaseHelper


    private var _binding: FragmentServicesConstantBinding? = null
    lateinit var adapter: AdapterConst

    private val binding get() = _binding!!
var  types : Int =0
    lateinit var Mlist:ArrayList<ConstModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentServicesConstantBinding.inflate(inflater, container, false)

        mydb = DatabaseHelper(context)

        types= requireArguments().getInt("ConstType");



        if (types ==55) {

            binding.txttitle.setText("Category Items")
            Mlist = mydb.GetCategory()

        }
        else {
            binding.txttitle.setText("Companies")
            Mlist = mydb.GetCompanies()
        }

        var listItems: RecyclerView
        listItems = binding.ListItem
        listItems.layoutManager = LinearLayoutManager(context)
        listItems.setHasFixedSize(true)
        listItems!!.isNestedScrollingEnabled = true


        adapter = AdapterConst()

        listItems.adapter = adapter
        context?.let { adapter.setItems(Mlist, it) }


binding.btnRefresh.setOnClickListener()
{
    if (types ==55) {

        binding.txttitle.setText("Category Items")
        Mlist = mydb.GetCategory()

    }
    else {
        binding.txttitle.setText("Companies")
        Mlist = mydb.GetCompanies()
    }

    context?.let { it1 -> adapter.setItems(Mlist, it1) }


}

        binding.btnAdd.setOnClickListener()
        {
            val bundle = Bundle()

            bundle.putInt("ConstType", types)

            bundle.putInt("ConstId", 0)
            var fragement = FragmentDialogConst()

            fragement.setArguments(bundle)
            getActivity()?.let { it1 -> fragement.show(it1.supportFragmentManager, "customfragment") }



        }

        binding.btnClose.setOnClickListener()
        {
            getActivity()?.finish()
        }
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onResume() {
        super.onResume()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}