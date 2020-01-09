package com.example.testfirebaseadd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.homepage.ui.information_centre.RecyclableItem
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val REQUIRED = "Required"
    //access database table
    private var recyclableItemDatabase: DatabaseReference? = null//change
    //to get the current database pointer
    private var recyclableItemReference: DatabaseReference? = null//change
    private var recyclableItemListener: ChildEventListener? = null//change

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to get the root folder
        recyclableItemDatabase = FirebaseDatabase.getInstance().reference//change
        //to access to the table
        recyclableItemReference = FirebaseDatabase.getInstance().getReference("location")//change


        fab.setOnClickListener {
            addNewRecyclableItem()
        }
    }

    private fun addNewRecyclableItem(){
        val itemName = editTextItemName.text.toString() //change
        val imageLink = editTextItemImageString.text.toString() //change

        val recyclableItem = RecyclableItem(itemName
            ,imageLink) //change

        val recyclableItemValues = recyclableItem.toMap() //change
        val childUpdates = HashMap<String, Any>()

        val key = recyclableItemDatabase!!.child("recyclableItem").push().key //change

        childUpdates.put("/recyclableItem/" + key, recyclableItemValues) //MUST change

        recyclableItemDatabase!!.updateChildren(childUpdates) //change
    }
}
