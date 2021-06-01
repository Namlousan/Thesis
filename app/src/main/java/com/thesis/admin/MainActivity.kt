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
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

    class MainActivity : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth
        var databaseReferenceAdmin : DatabaseReference? = null
        var databaseAdmin: FirebaseDatabase? = null

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();

        authAdmin = FirebaseAuth.getInstance()
        val currentUser = authAdmin.currentUser

        login()
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
                                    authAdmin = FirebaseAuth.getInstance()
                                    databaseAdmin = FirebaseDatabase.getInstance()
                                    val currentUserAdmin = authAdmin.currentUser
                                    val registeredUserID = currentUserAdmin.getUid()

                                    databaseReferenceAdmin = databaseAdmin?.reference!!.child("AdminDB").child(registeredUserID)

                                    if(currentUserAdmin.isEmailVerified){
                                        databaseReferenceAdmin?.addValueEventListener(object : ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                val AdminDB = snapshot.child("As").value.toString()
                                                if (AdminDB.equals("Admin")) {

                                                    val df = SimpleDateFormat("EEE MM/dd/yyyy hh:mm.ss aa")
                                                    val c = Calendar.getInstance()
                                                    val str_time: String = df.format(c.time)
                                                    val name = snapshot.child("AdminName").value.toString()
                                                    val id = snapshot.child("AdminID").value.toString()
                                                    val As = snapshot.child("As").value.toString()

                                                    val database = FirebaseDatabase.getInstance()
                                                    val myRef = database.getReference("AdminLogs")
                                                    val map: HashMap<String, String?> = hashMapOf(
                                                            "Name" to name,
                                                            "Date" to str_time,
                                                            "As" to As,
                                                            "ID" to id,
                                                            "LogStat" to "LogIn"
                                                    )
                                                    myRef.push().setValue(map)

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
                    startActivity(Intent(this@MainActivity, ForgotAdminPass::class.java))
                    finish()
                }

            }

        }

