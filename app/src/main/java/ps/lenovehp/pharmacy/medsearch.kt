package ps.lenovehp.pharmacy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.lenovehp.pharmacy.adapter.DatabaseHelper
import ps.lenovehp.pharmacy.adapter.ItemModel
import ps.lenovehp.pharmacy.databinding.ActivityItemSearchBinding
import ps.lenovehp.pharmacy.group.ash.AdaptermedSearch
import java.lang.Exception


class medsearch : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityItemSearchBinding
    lateinit var adapter: AdaptermedSearch
    lateinit var  mydb : DatabaseHelper
    lateinit var Mlist:ArrayList<ItemModel>
    lateinit var MlistFilter:ArrayList<ItemModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout((width * .9).toInt(), (height * .70).toInt())

        binding = ActivityItemSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)



        mydb  = DatabaseHelper(this)

try {

    Mlist = mydb.GetItems(0)


    var listItems: RecyclerView

    listItems = findViewById(R.id.serach_list)

    listItems.layoutManager = LinearLayoutManager(this)
    listItems.setHasFixedSize(true)
    listItems!!.isNestedScrollingEnabled = true


    adapter = AdaptermedSearch()

    listItems.adapter = adapter
    MlistFilter = ArrayList<ItemModel>()

    MlistFilter.addAll(Mlist)

    adapter.setItems(MlistFilter, this)


    var txt_item_name: EditText
    txt_item_name = findViewById(R.id.txt_item_name)

    txt_item_name.addTextChangedListener(object:TextWatcher
    {
        override fun afterTextChanged(s: Editable?) {
        if (Mlist != null) {


            try {

                var xx: String? = txt_item_name.getText().toString().trim()

                if (xx != null) {
                    xx = convertArabic(xx.toUpperCase())
                }



                MlistFilter.clear()
                for (mr in Mlist) {
                    if (mr.generalName.toString().toUpperCase().contains(xx.toString())) {
                        MlistFilter.add(mr)

                    }
                }

                adapter.setItems(MlistFilter, applicationContext)


            } catch (ex: Exception) {
            }


        }
    }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    })

}
catch (e:Exception)
{
    Toast.makeText(applicationContext,e.message.toString(), Toast.LENGTH_LONG).show()
}
    }

    override fun onSupportNavigateUp(): Boolean {

        return false
    }


    fun convertArabic(arabicStr: String): String? {
        val chArr = arabicStr.toCharArray()
        val sb = StringBuilder()
        for (ch in chArr) {
            if (Character.isDigit(ch)) {
                sb.append(Character.getNumericValue(ch))
            }else if (ch == '٫'){
                sb.append(".")
            }

            else {
                sb.append(ch)
            }
        }
        return sb.toString()
    }
}