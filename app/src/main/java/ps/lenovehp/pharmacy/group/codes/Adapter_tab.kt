package ps.lenovehp.pharmacy.group.codes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*


class Adapter_tab(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {
    var mlist: ArrayList<mytab> = ArrayList<mytab>()
    fun addtab(tab: mytab) {
        mlist.add(tab)
    }

    override fun getItem(position: Int): Fragment {
        return mlist[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mlist[position].tab_name
    }

    override fun getCount(): Int {
        return mlist.size
    }
}
