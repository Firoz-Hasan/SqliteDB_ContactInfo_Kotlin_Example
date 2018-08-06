package com.example.firozhasan.kotlin_sqlitedb_example.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.SimpleCursorAdapter
import com.example.firozhasan.kotlin_sqlitedb_example.model.PersonalDetails
import java.util.ArrayList

class DataStorage(context: Context) {
    private val dbHelper: DBHelper
    private var sqliteDB: SQLiteDatabase? = null
    internal var simpleCursorAdapter: SimpleCursorAdapter? = null

    //String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
   /* val allContacts: ArrayList<PersonalDetails>
        get() {

            val contactModels = ArrayList<PersonalDetails>()
            this.dbOpen()

            val selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME


            val cursor = sqliteDB!!.rawQuery(selectQuery, null)

            if (cursor != null && cursor.count > 0) {

                cursor.moveToFirst()

                for (i in 0 until cursor.count) {

                    val contactID = cursor.getInt(cursor.getColumnIndex(DBHelper.CONTACT_COL_ID))
                    val contactName = cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_NAME))
                    val contactMblNumber = cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_MOBILE_NUMBER))
                    val contactEmailAddress = cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_EMAIL_ADDRESS))
                    val contactModel = PersonalDetails(contactName, contactMblNumber, contactEmailAddress, contactID)
                    contactModels.add(contactModel)
                    cursor.moveToNext()
                }
            }
            sqliteDB!!.close()
            this.dbClose()
            return contactModels
        }*/

    init {
        dbHelper = DBHelper(context)

    }

    private fun dbOpen() {
        sqliteDB = dbHelper.writableDatabase

    }

    private fun dbClose() {
        dbHelper.close()
    }


    fun insertContact(model: PersonalDetails): Boolean {
        this.dbOpen()

        val cvProfileValue = ContentValues()
        cvProfileValue.put(DBHelper.CONTACT_COL_NAME, model.name)
        cvProfileValue.put(DBHelper.CONTACT_COL_MOBILE_NUMBER, model.phoneNumber)
        cvProfileValue.put(DBHelper.CONTACT_COL_EMAIL_ADDRESS, model.email)

        val inserted = sqliteDB!!.insert(DBHelper.TABLE_NAME, null, cvProfileValue)

        this.dbClose()

        return inserted > 0

    }

    fun getAllPersonalDetails(): ArrayList<PersonalDetails> {

        val detailsLists = ArrayList<PersonalDetails>()
        this.dbOpen()

        val selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME


        val cursor = sqliteDB?.rawQuery(selectQuery, null)

        if (cursor != null && cursor!!.getCount() > 0) {

            cursor!!.moveToFirst()

            for (i in 0 until cursor!!.getCount()) {

                val contactID = cursor!!.getInt(cursor!!.getColumnIndex(DBHelper.CONTACT_COL_ID))
                val contactName = cursor!!.getString(cursor!!.getColumnIndex(DBHelper.CONTACT_COL_NAME))
                val contactMblNumber = cursor!!.getString(cursor!!.getColumnIndex(DBHelper.CONTACT_COL_MOBILE_NUMBER))
                val contactEmailAddress = cursor!!.getString(cursor!!.getColumnIndex(DBHelper.CONTACT_COL_EMAIL_ADDRESS))
                //String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                val details = PersonalDetails(contactName, contactMblNumber, contactEmailAddress, contactID)
                detailsLists.add(details)
                cursor!!.moveToNext()
            }
        }
        sqliteDB?.close()
        this.dbClose()
        return detailsLists
    }

    fun deleteRow(value: Int) {
        this.dbOpen()
        sqliteDB!!.execSQL("DELETE FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.CONTACT_COL_ID + "='" + value + "'")
        this.dbOpen()
    }

    fun updateContact(contactID: Int, contactModel: PersonalDetails): Boolean {
        this.dbOpen()

        val contactValue = ContentValues()
        contactValue.put(DBHelper.CONTACT_COL_NAME, contactModel.name)
        contactValue.put(DBHelper.CONTACT_COL_EMAIL_ADDRESS, contactModel.email)
        contactValue.put(DBHelper.CONTACT_COL_MOBILE_NUMBER, contactModel.phoneNumber)
        //cvDoctorValue.put(DBHelper.COL_FLAG, doctorModel.getFlag());

        // long inserted=database.insert(DBHelper.TABLE_NAME_DOCTOR,null,cvDoctorValue);

        val updated = sqliteDB!!.update(DBHelper.TABLE_NAME, contactValue, DBHelper.CONTACT_COL_ID + "= " + contactID, null).toLong()
        this.dbClose()

        return updated > 0

    }

}