package com.example.answerandquestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginactivity : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var login:Button
    private lateinit var tosignup:TextView

    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()

        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        login=findViewById(R.id.button)
        tosignup=findViewById(R.id.to_signup)

        tosignup.setOnClickListener{
            val intent = Intent (this, signup::class.java)
            startActivity(intent)
        }

        login.setOnClickListener{
            val email = email.text.toString()
            val password = password.text.toString()

            login(email,password)
        }
    }

    private fun login(email:String,password:String){
        //logic to login
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home
                    val intent=Intent(this@loginactivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@loginactivity, "error occured", Toast.LENGTH_SHORT).show()
                }
            }

    }
}