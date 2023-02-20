package ps.lenovehp.pharmacy.adapter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.collections.ArrayList

private const val DATABASE_NAME = "PharmacyDB.db"
private const val DATABASE_VERSION =1

const val TABLE_NAME_USER = "user_tb"
const val TABLE_NAME_ITEM = "item_tb"


const val TABLE_NAME_CategoryItem = "CategoryItem"
const val TABLE_NAME_Companies = "Companies"

const val TABLE_NAME_Invoice = "Invoice_tb"
const val TABLE_NAME_Invoice_item = "Invoice_item"

public class DatabaseHelper(
    context: Context?

) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
           db.execSQL("create table if not exists " + TABLE_NAME_USER + " (ID INTEGER PRIMARY  KEY AUTOINCREMENT,  user_name  TEXT , user_login TEXT , user_password TEXT , birth_date TEXT ,State_health INTEGER)")

            db.execSQL("create table if not exists " + TABLE_NAME_CategoryItem + " (ID INTEGER PRIMARY  KEY AUTOINCREMENT,  Category_name  TEXT , Category_descrption TEXT )")

            db.execSQL("create table if not exists " + TABLE_NAME_Companies + " (ID INTEGER PRIMARY  KEY AUTOINCREMENT,  Company_name  TEXT , Company_descrption TEXT )")

            db.execSQL("create table if not exists " + TABLE_NAME_ITEM + " (ID INTEGER PRIMARY  KEY AUTOINCREMENT, " +
                    " generalName  TEXT , tradename TEXT ,  company NUMBER, category NUMBER ,  productionDate TEXT,  expirationdate TEXT , price Float, description TEXT,unitsnumber Float , shelf TEXT , Img blob )")



            db.execSQL("create table if not exists " + TABLE_NAME_Invoice + " (ID INTEGER PRIMARY  KEY AUTOINCREMENT, " +
                    " inv_date  TEXT , guest_name TEXT ,  inv_type INTEGER ,  TotalInvoice Float  )")

            db.execSQL("create table if not exists " + TABLE_NAME_Invoice_item + " (ID INTEGER PRIMARY  KEY AUTOINCREMENT, " +
                    " InvID  INTEGER , item_no INTEGER ,  item_name TEXT,  qty Float , price Float,amount Float  ,inv_type INTEGER  )")



        }


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        onCreate(db)


    }


    fun LoginData(user_login :String , user_password: String ) : LoginModel {
        val users = LoginModel(0, "", "", "","",0)

        val db = this.readableDatabase

        val cursor = db.rawQuery("select * from " + TABLE_NAME_USER +   " where user_login ='" + user_login + "' and user_password = '" + user_password + "'" , null)
        cursor.moveToNext()
        if (cursor.count != 0) {
            users.ID= cursor.getLong(cursor.getColumnIndex("ID"))
            users.user_name =cursor.getString(cursor.getColumnIndex("user_name"))
            users.user_login  = cursor.getString(cursor.getColumnIndex("user_login"))
            users.user_password  = cursor.getString(cursor.getColumnIndex("user_password"))
            users.birth_date=  cursor.getString(cursor.getColumnIndex("birth_date"))
            users.State_health=  cursor.getInt(cursor.getColumnIndex("State_health"))
        }
        return users
    }


fun getItem(id : Int) : ItemModel
{
    val model = ItemModel(
        0, "", "", 0,0,"",
        "",(0).toFloat(),"",(0).toFloat(),"",null)

    val db = this.readableDatabase

    val cursor = db.rawQuery("select * from " + TABLE_NAME_ITEM +   " where ID =" +  id  , null)
    cursor.moveToNext()
    if (cursor.count != 0) {
        model.ID= cursor.getInt(cursor.getColumnIndex("ID"))
        model.generalName =cursor.getString(cursor.getColumnIndex("generalName"))
        model.tradename  = cursor.getString(cursor.getColumnIndex("tradename"))
        model.company  = cursor.getInt(cursor.getColumnIndex("company"))
        model.category=  cursor.getInt(cursor.getColumnIndex("category"))
        model.productionDate=  cursor.getString(cursor.getColumnIndex("productionDate"))
        model.expirationdate=  cursor.getString(cursor.getColumnIndex("expirationdate"))
        model.price=  cursor.getFloat(cursor.getColumnIndex("price"))
        model.description=  cursor.getString(cursor.getColumnIndex("description"))
        model.unitsnumber=  cursor.getFloat(cursor.getColumnIndex("unitsnumber"))
        model.shelf=  cursor.getString(cursor.getColumnIndex("shelf"))



    }
    return model
}
    fun GetuserInfor(user_id :Long ) : LoginModel {
        val users = LoginModel(0, "", "", "","",0)

        val db = this.readableDatabase

        val cursor = db.rawQuery("select * from " + TABLE_NAME_USER +   " where ID =" +  user_id  , null)
        cursor.moveToNext()
        if (cursor.count != 0) {
            users.ID= cursor.getLong(cursor.getColumnIndex("ID"))
            users.user_name =cursor.getString(cursor.getColumnIndex("user_name"))
            users.user_login  = cursor.getString(cursor.getColumnIndex("user_login"))
            users.user_password  = cursor.getString(cursor.getColumnIndex("user_password"))
            users.birth_date=  cursor.getString(cursor.getColumnIndex("birth_date"))
            users.State_health=  cursor.getInt(cursor.getColumnIndex("State_health"))
        }
        return users
    }

    fun User_Save(user: LoginModel) {
        val db = this.writableDatabase
        try {
            val cursor = db.rawQuery("select * from " + TABLE_NAME_USER  + " where Id =" +user.ID, null)
            val contentvalues = ContentValues()
            contentvalues.put("user_name", user.user_name)
            contentvalues.put("user_login", user.user_login)
            contentvalues.put("user_password", user.user_password)
            contentvalues.put("birth_date", user.birth_date)
            contentvalues.put("State_health", user.State_health)
            if (cursor.count == 0) {
                db.insert(TABLE_NAME_USER, null, contentvalues)
            } else {
                db.update(TABLE_NAME_USER, contentvalues, "  Id =" +user.ID, null)
            }
        } catch (ex: Exception) {
            throw ExceptionInInitializerError(ex.message.toString())
        }
    }


    fun GetCategory() :ArrayList<ConstModel> {
        val categoryList = ArrayList<ConstModel>()



        val db = this.readableDatabase

        val cursor = db.rawQuery("select * from " + TABLE_NAME_CategoryItem  , null)
        //cursor.moveToNext()
        while (cursor.moveToNext() ) {


            var mrow = ConstModel(0,"","",1)

            mrow.Id =cursor.getInt(cursor.getColumnIndex("ID"))
            mrow.const_name = cursor.getString(cursor.getColumnIndex("Category_name"))
            mrow.descrption=cursor.getString(cursor.getColumnIndex("Category_descrption"))
            mrow.types=55
            categoryList.add(mrow)

        }
        return categoryList
    }


    fun GetCompanies() :ArrayList<ConstModel> {
        val CompaniesList = ArrayList<ConstModel>()



        val db = this.readableDatabase

        val cursor = db.rawQuery("select * from " + TABLE_NAME_Companies  , null)
        //cursor.moveToNext()
        while (cursor.moveToNext() ) {


            var mrow = ConstModel(0,"","",2)

            mrow.Id =cursor.getInt(cursor.getColumnIndex("ID"))
            mrow.const_name = cursor.getString(cursor.getColumnIndex("Company_name"))
            mrow.descrption=cursor.getString(cursor.getColumnIndex("Company_descrption"))
            mrow.types=60
            CompaniesList.add(mrow)

        }
        return CompaniesList
    }

    fun GetConst(ID :Int? , types : Int?) : ConstModel {
        val category = ConstModel(0, "", "",0)
        var cursor : Cursor
        val db = this.readableDatabase
        if (types==55)
         cursor = db.rawQuery("select * from " + TABLE_NAME_CategoryItem +   " where ID =" +  ID  , null)
        else
         cursor = db.rawQuery("select * from " + TABLE_NAME_Companies +   " where ID =" +  ID  , null)
        cursor.moveToNext()



        if (cursor.count != 0) {
            if (types==55) {
                category.Id = cursor.getInt(cursor.getColumnIndex("ID"))
                category.const_name = cursor.getString(cursor.getColumnIndex("Category_name"))
                category.descrption = cursor.getString(cursor.getColumnIndex("Category_descrption"))
            }
            else
            {
                category.Id = cursor.getInt(cursor.getColumnIndex("ID"))
                category.const_name = cursor.getString(cursor.getColumnIndex("Company_name"))
                category.descrption = cursor.getString(cursor.getColumnIndex("Company_descrption"))
            }
        }
        return category
    }


    fun Category_Save(model: ConstModel) {
        val db = this.writableDatabase


        try {


            val cursor = db.rawQuery("select * from " + TABLE_NAME_CategoryItem + " where ID =" +model.Id, null)
            val contentvalues = ContentValues()
            contentvalues.put("Category_name", model.const_name)
            contentvalues.put("Category_descrption", model.descrption)
            if (cursor.count == 0) {
                db.insert(TABLE_NAME_CategoryItem, null, contentvalues)
            } else {
                db.update(TABLE_NAME_CategoryItem, contentvalues,  "  ID =" + model.Id , null)
            }
        } catch (ex: Exception) {
            throw ExceptionInInitializerError(ex.message.toString())
        }
    }
    fun Companies_Save(model: ConstModel) {
        val db = this.writableDatabase


        try {


            val cursor = db.rawQuery("select * from " + TABLE_NAME_Companies + " where ID =" +model.Id, null)
            val contentvalues = ContentValues()
            contentvalues.put("Company_name", model.const_name)
            contentvalues.put("Company_descrption", model.descrption)
          //  contentvalues.put("ID", model.Id)

            if (cursor.count == 0) {
                db.insert(TABLE_NAME_Companies, null, contentvalues)
            } else {
                db.update(TABLE_NAME_Companies, contentvalues, "  ID =" +model.Id, null)
            }
        } catch (ex: Exception) {
            throw ExceptionInInitializerError(ex.message.toString())
        }
    }

    fun deleteCategory(id: Int)
    {
        val db = this.writableDatabase


        try {
            var sql = ""
            sql = " delete from " + TABLE_NAME_CategoryItem
           sql = sql + "  where ID=$id"

            db.execSQL(sql)



        } catch (ex: Exception) {
            throw ExceptionInInitializerError(ex.message.toString())
        }
    }

    fun deleteCompanies(id : Int)
    {
        val db = this.writableDatabase


        try {
            var sql = ""
            sql = " delete from " + TABLE_NAME_Companies
            sql = sql + " where ID=$id"

            db.execSQL(sql)



        } catch (ex: Exception) {
            throw ExceptionInInitializerError(ex.message.toString())
        }
    }
    fun saveItem(model: ItemModel) : Int
    {

        val db = this.writableDatabase


        try {


            val cursor = db.rawQuery("select * from " + TABLE_NAME_ITEM + " where ID =" +model.ID, null)
            val contentvalues = ContentValues()
            contentvalues.put("generalName", model.generalName)
            contentvalues.put("tradename", model.tradename)
            contentvalues.put("company", model.company)
            contentvalues.put("category", model.category)
            contentvalues.put("productionDate", model.productionDate)
            contentvalues.put("expirationdate", model.expirationdate)
            contentvalues.put("price", model.price)
            contentvalues.put("description", model.description)
            contentvalues.put("shelf", model.shelf)
            contentvalues.put("unitsnumber", model.unitsnumber)


            if (cursor.count == 0) {
                db.insert(TABLE_NAME_ITEM, null, contentvalues)
                model.ID = getLastAddedRowId(db)
            } else {
                db.update(TABLE_NAME_ITEM, contentvalues, "  ID =" +model.ID, null)
            }
        } catch (ex: Exception) {
            throw ExceptionInInitializerError(ex.message.toString())
        }
            return  model.ID
    }

    fun itemDelete(item_no : Long)
    {
        val db = this.writableDatabase


        try {
            var sql = ""
            sql = " delete from " + TABLE_NAME_ITEM
            sql = sql + " where ID=$item_no"

            db.execSQL(sql)



        } catch (ex: Exception) {
            throw ExceptionInInitializerError(ex.message.toString())
        }
    }

    fun GetItems(categoryId : Int) :ArrayList<ItemModel> {
        val ItemList = ArrayList<ItemModel>()



        val db = this.readableDatabase

        var sql : String= "";
        sql = "select * from " + TABLE_NAME_ITEM
        if (categoryId !=0)
            sql = sql + " where category ="+ categoryId.toString()
        val cursor = db.rawQuery(sql  , null)
        //cursor.moveToNext()
        while (cursor.moveToNext() ) {


            var mrow = ItemModel(0,"","",0,0,""
                ,"",(0).toFloat(),"",(0).toFloat(),"",null)


            mrow.ID =cursor.getInt(cursor.getColumnIndex("ID"))
            mrow.generalName = cursor.getString(cursor.getColumnIndex("generalName"))
            mrow.tradename=cursor.getString(cursor.getColumnIndex("tradename"))
            mrow.productionDate=cursor.getString(cursor.getColumnIndex("productionDate"))
            mrow.expirationdate=cursor.getString(cursor.getColumnIndex("expirationdate"))
            mrow.description=cursor.getString(cursor.getColumnIndex("description"))

            mrow.price=cursor.getFloat(cursor.getColumnIndex("price"))
            mrow.unitsnumber=cursor.getFloat(cursor.getColumnIndex("unitsnumber"))

            ItemList.add(mrow)

        }
        return ItemList
    }

    fun GetItemsBalance() : ArrayList<ItemBalanceModel>
    {
        val ItemList = ArrayList<ItemBalanceModel>()



        val db = this.readableDatabase

        var sql : String= "";
        sql = "select * from " + TABLE_NAME_ITEM

        val cursor = db.rawQuery(sql  , null)
        //cursor.moveToNext()
        while (cursor.moveToNext() ) {


            var mrow= ItemBalanceModel(0,"",0F,0F,0F,0F)


            mrow.id =cursor.getInt(cursor.getColumnIndex("ID"))
            mrow.item_name = cursor.getString(cursor.getColumnIndex("generalName"))
            mrow.qty_start_balance=cursor.getFloat(cursor.getColumnIndex("unitsnumber"))


            mrow.qty_puerchaes=getItemTrans(mrow.id,2)
            mrow.qty_sales=getItemTrans(mrow.id,1)


            mrow.qty_balance=  mrow.qty_start_balance +  mrow.qty_puerchaes -  mrow.qty_sales

            ItemList.add(mrow)

        }
        return ItemList
    }


    fun getItemTrans (item_no : Int , types : Int) : Float
    {
        var amt = 0F
        val db = this.readableDatabase
        var sql = ""
        sql =
            "select sum(qty)  from " + TABLE_NAME_Invoice_item.toString() +  " where inv_type =" + types + " and item_no = " +  item_no
        val cursor = db.rawQuery(sql, null)
        if (cursor != null) {
            cursor.moveToFirst()
            amt = cursor.getFloat(0)

        } else {
            amt = 0F
        }
        return amt
    }
    fun DeleteInvoice(_serial_no: Int, _inv_type: Int) {
        val db = this.writableDatabase
        var sql = " delete from " + TABLE_NAME_Invoice
            .toString() + " where ID =" + _serial_no
            .toString() + " and inv_type=" + _inv_type
        db.execSQL(sql)
        sql = " delete from " + TABLE_NAME_Invoice_item
            .toString() + " where invID =" + _serial_no
            .toString() + " and inv_type=" + _inv_type
        db.execSQL(sql)

    }


    fun SaveInvoice(
        invoice_tb: InvoiceModel,
        invoice_item: ArrayList<InvoiceItemModel>,

    ): Int? {

        try {
            val db = this.writableDatabase
            val cursor = db.rawQuery("select * from " + TABLE_NAME_Invoice + " where ID =" + invoice_tb.ID, null)
            val contentvalues = ContentValues()
            contentvalues.put("inv_date", invoice_tb.inv_date)
            contentvalues.put("TotalInvoice", invoice_tb.TotalInvoice)
            contentvalues.put("guest_name", invoice_tb.guest_name)
            contentvalues.put("inv_type", invoice_tb.inv_type)




            if (cursor.count == 0) {
                  db.insert(TABLE_NAME_Invoice, null, contentvalues).toInt()

                invoice_tb.ID= getLastAddedRowId(db)
                //invoice_tb.ID = getMaxInvoice()
            } else {
                db.update(TABLE_NAME_Invoice, contentvalues, "  ID =" +invoice_tb.ID, null)
            }



            for (  item in invoice_item) {
                val cursor2 = db.rawQuery(
                    "select * from " + TABLE_NAME_Invoice_item + " where ID =" + item.ID + " and InvID =" + invoice_tb.ID,
                    null
                )
                val contentvalues2 = ContentValues()
                contentvalues2.put("InvID", invoice_tb.ID)
                contentvalues2.put("item_no", item.item_no)
                contentvalues2.put("item_name", item.item_name)
                contentvalues2.put("inv_type", item.inv_type)
                contentvalues2.put("qty", item.qty)
                contentvalues2.put("price", item.price)
                contentvalues2.put("amount", item.amount)




                if (cursor2.count == 0) {
                     db.insert(TABLE_NAME_Invoice_item, null, contentvalues2).toInt()
                } else {
                    db.update(TABLE_NAME_Invoice_item, contentvalues2, "  ID =" + item.ID + " InvID =" + invoice_tb.ID, null)
                }


            }


    }
        catch (e:Exception)
        {


        }
        return invoice_tb.ID
    }
    fun GetInvoice(inv_type: Int, serial_no: Int): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery(
            "select * from " + TABLE_NAME_Invoice.toString() + " where inv_type =" + inv_type.toString() + " and invID =" + serial_no.toString() + " order by ID",
            null
        )
    }

    fun GetInvoiceItems(inv_type: Int, serial_no: Int): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery(
            "select * from " + TABLE_NAME_Invoice_item.toString() + " where inv_type =" + inv_type.toString() + " and invID =" + serial_no.toString() + " order by ID",
            null
        )
    }


    fun getMaxInvoice(): Int {
        var Max_no = 0
        val db = this.readableDatabase
        var sql = ""
        sql =
            "select max(ID)  from " + TABLE_NAME_Invoice.toString()
        val cursor = db.rawQuery(sql, null)
        if (cursor != null) {
            cursor.moveToFirst()
            Max_no = cursor.getInt(0)

        } else {
            Max_no = 1
        }
        return Max_no
    }

    fun getLastAddedRowId(db : SQLiteDatabase): Int {
        val queryLastRowInserted = "select last_insert_rowid() "
        val cursor: Cursor = db.rawQuery(queryLastRowInserted, null)
        var _idLastInsertedRow = 0
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    _idLastInsertedRow = cursor.getInt(0)
                }
            } finally {
                cursor.close()
            }
        }
        return _idLastInsertedRow
    }

}