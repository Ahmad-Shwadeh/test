package ps.lenovehp.pharmacy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import ps.lenovehp.pharmacy.databinding.ActivityFirstBinding

class ActivityFirst : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


    }

    override fun onSupportNavigateUp(): Boolean {
      return false
    }
    fun firstCommite(){
      print("test")
    }
}