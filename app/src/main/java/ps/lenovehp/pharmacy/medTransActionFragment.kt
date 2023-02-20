package ps.lenovehp.pharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ps.lenovehp.pharmacy.databinding.FragmentItemTransActionBinding
import ps.lenovehp.pharmacy.group.codes.Adapter_sub_menu
import ps.lenovehp.pharmacy.group.codes.Model_Heder
import java.util.ArrayList

class medTransActionFragment : Fragment() {
    private var _binding: FragmentItemTransActionBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemTransActionBinding.inflate(inflater, container, false)

        val root: View = _binding!!.root


        var rv =binding.TransList //


        var Mlist = ArrayList<Model_Heder>()
        var model = Model_Heder(
            100,
            "Sales Item"

        )

        Mlist.add(model)


        model = Model_Heder(
            105,
            "Pershaers Item "

        )

        Mlist.add(model)


        model = Model_Heder(
            110,
            "Item Balance"

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