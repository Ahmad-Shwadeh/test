package ps.lenovehp.pharmacy

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ps.lenovehp.pharmacy.R
import ps.lenovehp.pharmacy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment : Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragment = LoginFragment()
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
       ft.replace(R.id.myconterner, LoginFragment())
       ft.commit()



    }




}