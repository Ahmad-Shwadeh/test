package ps.lenovehp.pharmacy.group.codes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import ps.lenovehp.pharmacy.DataActivity
import ps.lenovehp.pharmacy.medTransActionFragment
import ps.lenovehp.pharmacy.MainActivity
import ps.lenovehp.pharmacy.R
import ps.lenovehp.pharmacy.databinding.ActivityMainMenuBinding
import ps.lenovehp.pharmacy.group.account.UserInformation


class MainActivityMenu : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
       val navView: NavigationView = binding.navView






       var tabLayout = findViewById<View>(R.id.tablayout) as TabLayout
      var  viewPager = findViewById<View>(R.id.viewpager) as ViewPager




        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE)
        val adapter = Adapter_tab(supportFragmentManager)
        adapter.addtab(mytab("Items", ItemsFragment()))
        adapter.addtab(mytab("Utlity", servecisFragment()))

        adapter.addtab(mytab("Item Trnas", medTransActionFragment()))

        viewPager.setAdapter(adapter)

        tabLayout.setupWithViewPager(viewPager)


        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.ItemNew ->
                {
                    val bundle = Bundle()
                    bundle.putInt("ActivityType", 1)
                    bundle.putInt("ItemId", 0)

                    val intent = Intent(this, DataActivity::class.java)
                    intent.putExtras(bundle)
                    this.startActivity(intent)
                    true
                }
                R.id.ItemList ->
                {
                    val bundle = Bundle()
                    bundle.putInt("ActivityType",5)
                    bundle.putInt("Category_no", 0)
                    val intent = Intent(this, DataActivity::class.java)
                    intent.putExtras(bundle)
                    this.startActivity(intent)
                    true
                }
                R.id.SearchByCategory->
                {

                    val bundle = Bundle()
                    bundle.putInt("ActivityType", 10)
                    val intent = Intent(this, DataActivity::class.java)
                    intent.putExtras(bundle)
                    this.startActivity(intent)
                    true
                }
            }


            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
       // return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

     //   Toast.makeText(this,item.title.toString(),Toast.LENGTH_LONG).show()
        return when (item.itemId) {
            R.id.userProfileMenu -> {
                val intent = Intent(this, UserInformation::class.java)
                this.startActivity(intent)

                true
            }
            R.id.SingOut -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                val sharedPreference = this?.getSharedPreferences("Pharmacy", Context.MODE_PRIVATE)

                var editor = sharedPreference?.edit()
                editor?.clear()
                editor?.apply()

                //editor?.commit()

                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }




    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener(
            object : NavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                    selectDrawerItem(menuItem)
                    return true
                }
            })
    }

    fun selectDrawerItem(menuItem: MenuItem) {



        menuItem.isChecked = true
        title = menuItem.title
        binding.drawerLayout.closeDrawers()

        Toast.makeText(this,menuItem.title.toString(),Toast.LENGTH_LONG).show()
    }

}