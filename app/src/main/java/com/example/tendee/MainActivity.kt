package com.example.tendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forgottext.setOnClickListener {
            Log.d("Main", "Attempting to be in forgot page")

            val intent = Intent(this, forgot_password::class.java)
            startActivity(intent)
        }



        signin_btn.setOnClickListener {
            val email = email_text.text.toString()
            val password = password_text.text.toString()

            Log.d("MainActivity", "Email is: " + email)
            Log.d("MainActivity", "Password is: $password")
//            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener {
//                    if(!it.isSuccessful) return@addOnCompleteListener
//
//                    Log.d("Main", "Successfully created user with uid: ${it.result.user.uid}")
//                }

        }



        newuser_textview.setOnClickListener {
            Log.d("MainActivity", "Attempting to show the register screen")

            val intent = Intent(this, SignUpAccount::class.java)
            startActivity(intent)
        }
    }
}
