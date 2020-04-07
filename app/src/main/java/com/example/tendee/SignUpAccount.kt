package com.example.tendee

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpAccount : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_account)
        auth = FirebaseAuth.getInstance()

        val createaccountbtn = findViewById<View>(R.id.createaccountbtn) as Button


        createaccountbtn.setOnClickListener {
            signUpUser()
        }



    }



    private fun signUpUser(){

        val email_phone_text = findViewById<View>(R.id.email_phone_text) as EditText
        val password_text = findViewById<View>(R.id.password_text) as EditText


        var email = email_phone_text.text.toString()
        var password = password_text.text.toString()



        if(email.isEmpty())
        {
            email_phone_text.error = "Please enter email"
            email_phone_text.requestFocus()
            return


        }

        if( password.isEmpty())
        {
            password_text.error = "Please enter password"
            password_text.requestFocus()
            return


        }



        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { tasks ->
                            if (tasks.isSuccessful) {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(
                                    baseContext, "Please try again. Make Sure to include numbers.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
    }
}