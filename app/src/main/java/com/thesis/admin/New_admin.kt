package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class New_admin : AppCompatActivity() {

    lateinit var authSecu: FirebaseAuth
    var databaseReferenceSecu : DatabaseReference? = null
    var databaseSecu: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_admin)

        authSecu = FirebaseAuth.getInstance()
        databaseSecu = FirebaseDatabase.getInstance()
        databaseReferenceSecu = databaseSecu?.reference!!.child("SecuDb")

        registeradmin()
    }

    private fun registeradmin() {

        val adminID = findViewById<EditText>(R.id.adminID)
        val adminName = findViewById<EditText>(R.id.adminName)
        val adminpass = findViewById<EditText>(R.id.adminpass)
        val emailaddress = findViewById<EditText>(R.id.emailaddress)
        val regbtn = findViewById<Button>(R.id.regbtn)
        val backbtn = findViewById<Button>(R.id.backbtn)


        regbtn.setOnClickListener {

            if (TextUtils.isEmpty(adminID.text.toString())) {
                adminID.setError("Please enter SG ID")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(adminName.text.toString())) {
                adminName.setError("Please enter SG Name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(emailaddress.text.toString())) {
                emailaddress.setError("Please enter Valid Email")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(adminpass.text.toString())) {
                adminpass.setError("Please enter Password")
                return@setOnClickListener
            }

            authSecu.createUserWithEmailAndPassword(emailaddress.text.toString(), adminpass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val currentUserAdmin = authSecu.currentUser
                            val currentUserSecuDb = databaseReferenceSecu?.child((currentUserAdmin?.uid!!))
                            currentUserSecuDb?.child("SecuID")?.setValue(adminID.text.toString())
                            currentUserSecuDb?.child("SecuName")?.setValue(adminName.text.toString())
                            currentUserSecuDb?.child("EmailAddress")?.setValue(emailaddress.text.toString())
                            currentUserSecuDb?.child("SecuPass")?.setValue(adminpass.text.toString())


                            Toast.makeText(this@New_admin, "Registration succes!", Toast.LENGTH_LONG).show()
                            finish()
                        }else {
                            Toast.makeText(
                                    this@New_admin,
                                    "Registration Failed, try agian!",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }
        backbtn.setOnClickListener{
            startActivity(Intent(this@New_admin, Administrators::class.java))
        }
    }
}
