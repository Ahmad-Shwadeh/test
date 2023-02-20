package ps.lenovehp.pharmacy.group.codes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import ps.lenovehp.pharmacy.databinding.FragmentServicesBinding
import java.util.ArrayList

class servecisFragment : Fragment() {
    private var _binding: FragmentServicesBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentServicesBinding.inflate(inflater, container, false)

        val root: View = _binding!!.root

        var rv =binding.ListItem //

        var Mlist = ArrayList<Model_Heder>()
        var model = Model_Heder(
            50,
            "User Profile"

        )

        Mlist.add(model)


        model = Model_Heder(
            55,
            "Category Items"

        )

        Mlist.add(model)


        model = Model_Heder(
            60,
            "Companies"

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