package com.example.homework9

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val items = ArrayList<MyDataClass>()

    val myAdapter = MyAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener { addBtn() }

        binding.mainRv.layoutManager = LinearLayoutManager(this)
        val adapter = myAdapter
        binding.mainRv.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                startActivity(Intent(this@MainActivity, SecondActivity::class.java))

            }
        })
    }


    private fun addBtn() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.custom_layout_add_dialog, null)

        val firstName = v.findViewById<EditText>(R.id.addFirstNameEt)
        val lastName = v.findViewById<EditText>(R.id.secondLastNameEt)
        val email = v.findViewById<EditText>(R.id.addEmailEt)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("OK") { dialog, _ ->

            val fName = firstName.text.toString()
            val lName = lastName.text.toString()
            val e = email.text.toString()

            if (fName.isNullOrEmpty() || lName.isNullOrEmpty() || e.isNullOrEmpty()) {
                Toast.makeText(this, "Empty!!!", Toast.LENGTH_SHORT).show()
            } else {
                items.add(MyDataClass("$fName", "$lName", "$e"))
                myAdapter.notifyDataSetChanged()
                Toast.makeText(this, "adding information success!!!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }
        addDialog.setNegativeButton("cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "canceled!!!", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

}