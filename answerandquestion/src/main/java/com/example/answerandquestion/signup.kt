package com.example.answerandquestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signup : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var signup: Button
    private lateinit var tologin: TextView

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()

        email=findViewById(R.id.email)
        username=findViewById(R.id.username)
        password=findViewById(R.id.password)
        signup=findViewById(R.id.button)
        tologin=findViewById(R.id.to_login)

        tologin.setOnClickListener{
            val intent = Intent (this, com.example.answerandquestion.loginactivity::class.java)
            startActivity(intent)
        }

        signup.setOnClickListener{
            val email = email.text.toString()
            val username=username.text.toString()
            val password = password.text.toString()

            signup1(email,username,password)
        }
    }

    private fun signup1(email:String,username:String,password:String) {
    //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    addUsertoDatabase(email,username,mAuth.currentUser?.uid!!)
                    //code for jumping to home
                    val intent=Intent(this@signup,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@signup, "error occured", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUsertoDatabase(email: String,username: String,uid:String){
        mDbRef=FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(Users(email,username,uid))

    }
}