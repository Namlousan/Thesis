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

    lateinit var authAdmin: FirebaseAuth
    var databaseReferenceAdmin : DatabaseReference? = null
    var databaseAdmin: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_admin)

        authAdmin = FirebaseAuth.getInstance()
        databaseAdmin = FirebaseDatabase.getInstance()
        databaseReferenceAdmin = databaseAdmin?.reference!!.child("AdminDB")

        registeradmin()
    }

    private fun registeradmin() {

        val adminID = findViewById<EditText>(R.id.adminID)
        val adminName = findViewById<EditText>(R.id.adminName)
        val adminpass = findViewById<EditText>(R.id.adminpass)
        val emailaddress = findViewById<EditText>(R.id.emailaddress)
        val admindept = findViewById<EditText>(R.id.admindept)
        val admindeptcode = findViewById<EditText>(R.id.admindeptcode)
        val regbtn = findViewById<Button>(R.id.regbtn)
        val backbtn = findViewById<Button>(R.id.backbtn)


        regbtn.setOnClickListener {

            if (TextUtils.isEmpty(adminID.text.toString())) {
                adminID.setError("Please enter Admin ID")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(adminName.text.toString())) {
                adminName.setError("Please enter Admin Name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(emailaddress.text.toString())) {
                emailaddress.setError("Please enter Valid Email")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(adminpass.text.toString())) {
                adminpass.setError("Please enter Password")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(admindept.text.toString())) {
                admindept.setError("Please enter What Department")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(admindeptcode.text.toString())) {
                admindeptcode.setError("Please enter DeptCode")
                return@setOnClickListener
            }

            authAdmin.createUserWithEmailAndPassword(emailaddress.text.toString(), adminpass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val currentUserAdmin = authAdmin.currentUser
                            val currentUserAdminDB = databaseReferenceAdmin?.child((currentUserAdmin?.uid!!))
                            currentUserAdminDB?.child("AdminID")?.setValue(adminID.text.toString())
                            currentUserAdminDB?.child("AdminName")?.setValue(adminName.text.toString())
                            currentUserAdminDB?.child("EmailAddress")?.setValue(emailaddress.text.toString())
                            currentUserAdminDB?.child("AdminPass")?.setValue(adminpass.text.toString())
                            currentUserAdminDB?.child("AdminDept")?.setValue(admindept.text.toString())
                            currentUserAdminDB?.child("AdminDeptCode")?.setValue(admindeptcode.text.toString())

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
