package ps.lenovehp.pharmacy.group.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import ps.lenovehp.pharmacy.adapter.DatabaseHelper
import ps.lenovehp.pharmacy.adapter.LoginModel
import ps.lenovehp.pharmacy.R
import ps.lenovehp.pharmacy.databinding.ActivityUserInformationBinding
import java.text.SimpleDateFormat
import java.util.*


class UserInformation : AppCompatActivity() {

        private lateinit var appBarConfiguration: AppBarConfiguration
        private lateinit var binding: ActivityUserInformationBinding
        var UserId : Long = 1

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityUserInformationBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setSupportActionBar(binding.toolbar)
            getUser()

        }


        @SuppressLint("NewApi")
        fun getUser()
        {


            val user_name = findViewById<EditText>(R.id.user_name)
            val user_login = findViewById<EditText>(R.id.user_login)
            val user_password = findViewById<EditText>(R.id.user_password)

            val user_birth_date = findViewById<DatePicker>(R.id.user_birth_date)
            val statushelth1 = findViewById<RadioButton>(R.id.radio0)
            val statushelth2 = findViewById<RadioButton>(R.id.radio1)


            val btnsave = findViewById<Button>(R.id.btn_save)
            val btnclose = findViewById<Button>(R.id.btn_close)

            var  mydb : DatabaseHelper = DatabaseHelper(this)

            var myuser =   mydb.GetuserInfor(UserId)

if (myuser.ID !=(0).toLong()) {

    user_name.setText(myuser?.user_name.toString())

    user_password.setText(myuser?.user_password)
    user_login.setText(myuser.user_login)

    if (myuser.State_health == 1)
        statushelth1.isChecked = true
    else
        statushelth2.isChecked = true


    val d1 = Date(myuser.birth_date)
    val dateFormatter = SimpleDateFormat(
        "dd/MM/yyyy"
    )


    var y: Int
    var m: Int
    var d: Int
    y = d1.year
    m = d1.month
    d = d1.day

    user_birth_date.init(y, m, d, null)

}
            btnsave.setOnClickListener()
            {


                if (user_name.text.toString()=="")
                {
                    Toast.makeText(this,"Invalid User Name", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                if (user_login.text.toString()=="")
                {
                    Toast.makeText(this,"Invalid User Login", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                if (user_password.text.toString()=="")
                {
                    Toast.makeText(this,"Invalid User Password", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                if (statushelth1.isChecked== false && statushelth2.isChecked== false)
                {
                    Toast.makeText(this,"Invalid User Helth Status", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                var birth_date:String =user_birth_date.dayOfMonth.toString()+ "/" + user_birth_date.month.toString() + "/" + user_birth_date.year.toString()
                var helth_status : Int
                if (statushelth1.isChecked== true)
                    helth_status=1
                else
                    helth_status=2






                var userModel = LoginModel(UserId,user_name.text.toString(), user_login.text.toString(), user_password.text.toString(), birth_date.toString(),helth_status)
                mydb.User_Save(userModel)
                Toast.makeText(this , "Save user information", Toast.LENGTH_LONG).show()
                finish()
            }
            btnclose.setOnClickListener()
            {
                finish()
            }

        }
        override fun onSupportNavigateUp(): Boolean {

            return  false
        }
    }
