package com.example.firozhasan.kotlin_sqlitedb_example.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.firozhasan.kotlin_sqlitedb_example.R
import com.example.firozhasan.kotlin_sqlitedb_example.database.DataStorage
import com.example.firozhasan.kotlin_sqlitedb_example.model.PersonalDetails
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    internal var ctName: EditText? = null
    internal var ctNumber: EditText? = null
    internal var ctEmail: EditText? = null
    internal var saveBT: Button? = null
    internal var updateBT: Button? = null
    internal var contactLL: LinearLayout? = null
    internal var newContactLL: LinearLayout? = null
    internal var name: String? = null
    internal var number: String? = null
    internal var email: String? = null
    internal var id: Int? = null
    internal var id_no: Int = 0
    internal val dataStorage = DataStorage(this)
    internal var personalDetailsLists: ArrayList<PersonalDetails>? = null

    companion object {
        val TAG = "Mainactivity"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializer()
        saveBT?.setOnClickListener {
            name = ctName?.text.toString()
            number = ctNumber?.text.toString()
            email = ctEmail?.text.toString()
            //  Toast.makeText(this, name, Toast.LENGTH_SHORT)
            Log.d("wow", name + " " + number + " " + email)
            insertData()
        }
        showPersonalDetails()
    }

    private fun showPersonalDetails() {
        personalDetailsLists = dataStorage.getAllPersonalDetails()
        Log.d("data", personalDetailsLists.toString())
    }

    fun initializer() {

        ctName = findViewById(R.id.nameET) as EditText
        ctNumber = findViewById(R.id.numberET) as EditText
        ctEmail = findViewById(R.id.emailET) as EditText
        saveBT = findViewById(R.id.saveBT) as Button
        contactLL = findViewById(R.id.contactlistLL) as LinearLayout
        newContactLL = findViewById(R.id.newContactLL) as LinearLayout
        updateBT = findViewById(R.id.updateBTU) as Button

    }

    private fun insertData() {
                id_no++
                id = id_no;
        val personalInfo = PersonalDetails(name!!, number!!, email!!, id!!)
        Log.d(TAG, "id: $id\nname: $name\nnumber: $number\nemail: $email")
      //  val dataStorage1: DataStorage? = null


         var result = dataStorage.insertContact(personalInfo)
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();
        if (result != true) {
            Toast.makeText(this, "Duplicate Entry, Your name or phone number or email has been used before", Toast.LENGTH_SHORT).show()
        }
    }
}
