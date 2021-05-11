    package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

    class MainActivity : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        authAdmin = FirebaseAuth.getInstance()

        val currentUserAdmin = authAdmin.currentUser
        if (currentUserAdmin != null) {
            startActivity(Intent(this@MainActivity, Home_menu::class.java))
            finish()
        }

        login()

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();
    }

        private fun login() {

            val button = findViewById<Button>(R.id.login)
            val emailaddress = findViewById<EditText>(R.id.Username)
            val adminpass = findViewById<EditText>(R.id.adminpass)


            button.setOnClickListener {

                if (TextUtils.isEmpty(emailaddress.text.toString())) {
                    emailaddress.setError("Please enter your email!")
                    return@setOnClickListener
                } else if (TextUtils.isEmpty(adminpass.text.toString())) {
                    adminpass.setError("Please enter password!")
                    return@setOnClickListener
                }

                authAdmin.signInWithEmailAndPassword(
                    emailaddress.text.toString(),
                    adminpass.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this@MainActivity, Home_menu::class.java))
                            finish()

                        } else {
                            Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

            val button2 = findViewById<Button>(R.id.cancel_mainscreen)
            button2.setOnClickListener {
                val alertdialog: AlertDialog = AlertDialog.Builder(this).create()
                alertdialog.setTitle("Exit")
                alertdialog.setMessage("Are you sure you want to exit?")
                alertdialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes") { dialog, which ->
                    finish()
                    dialog.dismiss()
                }
                alertdialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No") { dialog, which ->
                    dialog.dismiss()
                }
                alertdialog.show()
            }

            val forgot = findViewById<Button>(R.id.forgot)

            forgot.setOnClickListener {
                startActivity(Intent(this@MainActivity, RegisterNewClient::class.java))
                    finish()
                }

        }

}