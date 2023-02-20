package ps.lenovehp.pharmacy

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ps.lenovehp.pharmacy.adapter.DatabaseHelper
import ps.lenovehp.pharmacy.databinding.FragmentLoginBinding
import ps.lenovehp.pharmacy.group.account.UserInformation
import ps.lenovehp.pharmacy.group.codes.MainActivityMenu


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding ? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {



        }




    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root


        var mydb : DatabaseHelper= DatabaseHelper(context)


        var user_name = root.findViewById<EditText>(R.id.user_name)
        var user_password = root.findViewById<EditText>(R.id.user_password)

        var btn_login = root.findViewById<Button>(R.id.btn_login)

        var singIn = root.findViewById<TextView>(R.id.singIn)



        val sharedPref = context?.getSharedPreferences(
            "Pharmacy", Context.MODE_PRIVATE)

       // var id_user = sharedPref!!.getString("jhlk00").toString()

        //editor?.putLong("userID",LoginModel.ID)
        //editor?.putString("userName",LoginModel.user_name.toString())

        var id_user : Long=0

        var userName:String

        if (sharedPref != null) {
            id_user =sharedPref.getLong("userID",0)
            userName =sharedPref.getString("userName","").toString()
        }



        if ( id_user != (0).toLong()) {

            val intent = Intent(context, MainActivityMenu::class.java)
            startActivity(intent)

            //context.finish()

            var nn = context as Activity
            nn.finish()

        }


        btn_login.setOnClickListener()
        {

            if (user_name.text.toString().equals(""))
            {

                val builder = AlertDialog.Builder(context)
                builder.setTitle("أدخل اسم المستخدم")

                builder.setPositiveButton("موافق",
                    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })


                val alert = builder.create()


                alert.show()

                Toast.makeText(context,"ادخل اسم المستخدم",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (user_password.text.toString().equals(""))
            {

                val builder = AlertDialog.Builder(context)
                builder.setTitle("أدخل كلمة المرور")

                builder.setPositiveButton("موافق",
                    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })


                val alert = builder.create()


                alert.show()

                Toast.makeText(context,"ادخل اسم المستخدم",Toast.LENGTH_LONG).show()
                Toast.makeText(context,"ادخل كلمة المرور",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var LoginModel = mydb.LoginData(user_name.text.toString(), user_password.text.toString())

            if (LoginModel.ID== (0).toLong())
            {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("خطأ في اسم المستخدم او كلمة المرور")

                builder.setPositiveButton("موافق",
                    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })


                val alert = builder.create()


                alert.show()

                Toast.makeText(context,"خطأ في اسم المستخدم او كلمة المرور",Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }
            else
            {

                val sharedPreference = context?.getSharedPreferences("Pharmacy", Context.MODE_PRIVATE)
                var editor = sharedPreference?.edit()
                editor?.putLong("userID",LoginModel.ID)
                editor?.putString("userName",LoginModel.user_name.toString())

                //editor.putLong("l",100L)
                editor?.commit()

                val intent = Intent(it.context, MainActivityMenu::class.java)
                startActivity(intent)

            }



        }


        singIn.setOnClickListener()
        {

try {

    //var fragement = signinDialog()
    //getActivity()?.let { it1 -> fragement.show(it1.supportFragmentManager, "customfragment") }

    val intent = Intent(it.context, UserInformation::class.java)
    startActivity(intent)

}
catch (e: Exception)
{
    Toast.makeText(it.context , e.message.toString(), Toast.LENGTH_LONG).show()
}

        }
        return root


        //return inflater.inflate(R.layout.fragment_login, container, false)
    }


}