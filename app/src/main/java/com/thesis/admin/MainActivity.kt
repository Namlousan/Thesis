    package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

    class MainActivity : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth
        var databaseReferenceAdmin : DatabaseReference? = null
        var databaseAdmin: FirebaseDatabase? = null
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }



        authAdmin = FirebaseAuth.getInstance()
        val currentUser = authAdmin.currentUser
        if(currentUser != null){
            startActivity(Intent(this@MainActivity, Home_menu::class.java))
            finish()
        }

        login()
    }
            private fun login() {

                val button = findViewById<ImageButton>(R.id.loginme)
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
                                    authAdmin = FirebaseAuth.getInstance()
                                    databaseAdmin = FirebaseDatabase.getInstance()
                                    val currentUserAdmin = authAdmin.currentUser
                                    val registeredUserID = currentUserAdmin.getUid()

                                    databaseReferenceAdmin = databaseAdmin?.reference!!.child("AdminDB").child(registeredUserID)

                                    if(currentUserAdmin.isEmailVerified){
                                        databaseReferenceAdmin?.addValueEventListener(object : ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                val AdminDB = snapshot.child("as").value.toString()
                                                if (AdminDB.equals("Admin")) {
                                                    intent.putExtra("email", emailaddress.getText())
                                                    startActivity(Intent(this@MainActivity, Home_menu::class.java))

                                                }else {
                                                    Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_LONG)
                                                            .show()
                                                }

                                            }

                                            override fun onCancelled(error: DatabaseError) {
                                                TODO("Not yet implemented")
                                            }
                                        })
                                    }else{
                                        Toast.makeText(this@MainActivity, "Login failed! Please verify your Email", Toast.LENGTH_LONG)
                                                .show()
                                    }

                                } else {
                                    Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_LONG)
                                            .show()
                                }
                            }
                }

                val button2 = findViewById<ImageButton>(R.id.cancel_mainscreen)
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
                    startActivity(Intent(this@MainActivity, ForgotAdminPass::class.java))
                    finish()
                }

            }

        }

