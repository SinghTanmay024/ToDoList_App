package com.example.todolist

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var item : EditText
        lateinit var add : Button

        lateinit var listView : ListView

        var itemList = ArrayList<String>()

        var fileHelper = FileHelper()





        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,android.R.id.text1,itemList)
        listView.adapter = arrayAdapter

        add.setOnClickListener{
            var itemName : String = item.text.toString()
            itemList.add(itemName)
            item.setText("")
            fileHelper.writedata(itemList,applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { adapterView, view, position, l ->

            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                dialogInterface.cancel()

            })
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->

                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writedata(itemList,applicationContext)
            })

            alert.create().show()

        }





    }
}