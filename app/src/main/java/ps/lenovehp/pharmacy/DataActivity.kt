package ps.lenovehp.pharmacy

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import ps.lenovehp.pharmacy.databinding.ActivityDataBinding
import ps.lenovehp.pharmacy.group.codes.InvoiceFragment


class DataActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDataBinding
    private lateinit var fragment : Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val bundle =  intent.getExtras()

        val types: Int = bundle!!.getInt("ActivityType", 0)
        var Category_no : Int =0

        when(types) {
            1-> {
                val item_id: Int = bundle!!.getInt("ItemId", 0)
                bundle.putInt("ItemId",item_id)

                fragment = NewmedFrag()
                fragment.setArguments(bundle)
                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)
                ft.commit()
            }
            5-> {
                Category_no= bundle!!.getInt("Category_no", 0)


                fragment = medicinelist()

                val bundle = Bundle()
                bundle.putInt("Category_no", Category_no)
                fragment.setArguments(bundle)

                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)
                ft.commit()
            }
            10-> {
                fragment = CategoryFragment()
                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)
                ft.commit()
            }

            55-> {
                val bundle = Bundle()
                bundle.putInt("ConstType", types)

                fragment = services()
                fragment.setArguments(bundle)

                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)
                ft.commit()
            }
            60-> {
                val bundle = Bundle()
                bundle.putInt("ConstType", types)
                fragment = services()
                fragment.setArguments(bundle)

                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)

                ft.commit()
            }

            100-> {
                val bundle = Bundle()
                bundle.putInt("ConstType", types)
                bundle.putInt("inv_type", 1)

                fragment = InvoiceFragment()
                fragment.setArguments(bundle)

                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)

                ft.commit()
            }
            105-> {
                val bundle = Bundle()
                bundle.putInt("inv_type", 2)
                bundle.putInt("ConstType", types)
                fragment = InvoiceFragment()
                fragment.setArguments(bundle)

                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)

                ft.commit()
            }
            110-> {
                val bundle = Bundle()
                fragment = medbalfrag()

                val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.add(R.id.myconterner, fragment)

                ft.commit()
            }
        }

        binding.closebtn.setOnClickListener()
        {
            onBackPressed()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        return false
    }
    override fun onSupportNavigateUp(): Boolean {
        return false
    }
}