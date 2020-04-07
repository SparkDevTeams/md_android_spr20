package com.example.tendee


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class forgot_password: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        val resetpswd = findViewById<View>(R.id.reset_password) as Button


        resetpswd.setOnClickListener {

            forgotpassword()


        }

    }

    private fun forgotpassword(){

        val email_txt = findViewById<View>(R.id.email_text2) as TextView

        var email = email_txt.text.toString()

        if(email.isEmpty())
        {
            return
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->

            if(task.isSuccessful){
                Toast.makeText(this,"Email sent.",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}