package com.example.firebasecurd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasecurd.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class UpdateData : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // nhan du lieu tu form readdata
        val intent = intent
        val user_ : String? = intent.getStringExtra("user")
        val first_ : String? = intent.getStringExtra("first")
        val last_ : String? = intent.getStringExtra("last")
        val age_ : String? = intent.getStringExtra("age")
        binding.userName.setText(user_.toString())
        binding.firstName.setText(first_.toString())
        binding.lastname.setText(last_.toString())
        binding.age.setText(age_.toString())
        binding.updateBtn.setOnClickListener {
            val userName = binding.userName.text.toString()
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastname.text.toString()
            val age = binding.age.text.toString()
            updateData(userName,firstName,lastName,age)
        }
    }
    // thu tuc updatedata
    private fun updateData(userName: String, firstName: String, lastName:
    String, age: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "firstName" to firstName,
            "lastName" to lastName,
            "age" to age
        )
        database.child(userName).updateChildren(user).addOnSuccessListener {
            // binding.userName.text.clear()
            // binding.firstName.text.clear()
            // binding.lastname.text.clear()
            // binding.age.text.clear()
            Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}