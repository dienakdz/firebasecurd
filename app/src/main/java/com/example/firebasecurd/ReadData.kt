package com.example.firebasecurd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import com.example.firebasecurd.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class ReadData : AppCompatActivity() {
    private lateinit var binding : ActivityReadDataBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val data_ : String? = intent.getStringExtra("data")
        // binding.etusername.setText(data_.toString())
        readData(data_.toString())

        binding.button.setOnClickListener {
            val intent = Intent(this@ReadData, UpdateData::class.java)
            // truyen du lieu tu form da ta readData qua updateData
            intent.putExtra("user", binding.tvUser.getText().toString())
            intent.putExtra("first", binding.tvFirstName.text )
            intent.putExtra("last", binding.tvLastName.text )
            intent.putExtra("age", binding.tvAge.text )
            startActivity(intent)
        }
    }
    private fun readData(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(userName).get().addOnSuccessListener {
            if (it.exists()){
                val firstname = it.child("firstName").value
                val lastName = it.child("lastName").value
                val age = it.child("age").value
                val usera = it.child("userName").value
                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                        // binding.etusername.setText(usera.toString())
                        binding.tvFirstName.text = firstname.toString()
                        binding.tvLastName.text = lastName.toString()
                        binding.tvAge.text = age.toString()
                        binding.tvUser.text = usera.toString()
            }else{
                Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}
