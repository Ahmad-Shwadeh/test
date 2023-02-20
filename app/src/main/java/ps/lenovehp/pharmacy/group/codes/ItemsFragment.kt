package ps.lenovehp.pharmacy.group.codes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager


import java.util.ArrayList

import ps.lenovehp.pharmacy.databinding.FragmentItemsBinding



class ItemsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentItemsBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)

           val root: View = _binding!!.root

        var rv =binding.ListItem


        var Mlist = ArrayList<Model_Heder>()
        var model = Model_Heder(
            1,
        "Add New Item"

        )

        Mlist.add(model)

         model = Model_Heder(
            5,
            "Items List "

        )

        Mlist.add(model)


        model = Model_Heder(
            10,
            "Serach By Item Category"

        )

        Mlist.add(model)

        val adapter_sub_menu = Adapter_sub_menu()
        if (rv != null) {
            rv.setAdapter(adapter_sub_menu)
        }
        adapter_sub_menu.setItems(Mlist, root.context)

        rv.setLayoutManager(LinearLayoutManager(context))

        return root




    }


}