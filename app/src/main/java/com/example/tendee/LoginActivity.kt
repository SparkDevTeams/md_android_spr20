package com.example.tendee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val signin_btn = findViewById<View>(R.id.signin_btn) as Button
        val forgottext = findViewById<View>(R.id.forgottext) as TextView
        val newuser_textview = findViewById<View>(R.id.newuser_textview) as TextView

        signin_btn.setOnClickListener {
            doLogin()
        }

        newuser_textview.setOnClickListener {
            Log.d("MainActivity", "Attempting to show the register screen")

            val intent = Intent(this, SignUpAccount::class.java)
            startActivity(intent)
            finish()
        }




        forgottext.setOnClickListener {
            Log.d("Main", "Attempting to be in forgot page")

            val intent = Intent(this, forgot_password::class.java)
            startActivity(intent)
        }



    }

    private fun doLogin() {
        val email_text = findViewById<View>(R.id.email_text) as EditText
        val password_text = findViewById<View>(R.id.password_text) as EditText


        var email = email_text.text.toString()
        var password = password_text.text.toString()



        if( email.isEmpty())
        {
            email_text.error = "Please enter email"
            email_text.requestFocus()
            return


        }

        if( password.isEmpty())
        {
            password_text.error = "Please enter password"
            password_text.requestFocus()
            return


        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's informatio
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser:FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }else{
                Toast.makeText(baseContext, "Please Verify email addr",
                    Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(baseContext, "Login Failed.",
                Toast.LENGTH_SHORT).show()
        }
    }

}
