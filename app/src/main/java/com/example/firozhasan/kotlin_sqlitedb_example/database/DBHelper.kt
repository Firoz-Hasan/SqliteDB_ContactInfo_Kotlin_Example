package com.example.firozhasan.kotlin_sqlitedb_example.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.firozhasan.kotlin_sqlitedb_example.ui.MainActivity


/**
 * Created by FIROZ HASAN on 8/1/2017.
 */
/* dhhelper class contains database name, table name, db version
* sqlite db create & upgrade sql query
*
* */

class DBHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CONTACT)
        Log.d(MainActivity.TAG, CREATE_TABLE_CONTACT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    companion object {

        val DATABASE_NAME = "contactTest.db"
        val DATABASE_VERSION = 1

        val TABLE_NAME = "contact"

        val CONTACT_COL_ID = "_id"
        val CONTACT_COL_MOBILE_NUMBER = "MobileNumber"
        val CONTACT_COL_EMAIL_ADDRESS = "EmailAddress"
        val CONTACT_COL_NAME = "Name"

        internal val CREATE_TABLE_CONTACT = "CREATE TABLE " + TABLE_NAME + " ( " +
                CONTACT_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                CONTACT_COL_MOBILE_NUMBER + " TEXT ," +
                CONTACT_COL_EMAIL_ADDRESS + " TEXT ," +
                CONTACT_COL_NAME + " TEXT )"
    }

}
