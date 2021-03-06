package com.thesis.admin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegAdmin : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth
    var databaseReferenceAdmin : DatabaseReference? = null
    var databaseAdmin: FirebaseDatabase? = null

    var radioGroup: RadioGroup? = null
    var radioButton: RadioButton? = null
    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_admin)


        radioGroup = findViewById(R.id.radioGroup)
        textView = findViewById(R.id.asadmin)

        val buttonApply: Button = findViewById(R.id.button_apply)
        buttonApply.setOnClickListener {
            val radioId = radioGroup?.getCheckedRadioButtonId()
            radioButton = findViewById(radioId!!)
            textView?.setText("Position: " + radioButton?.getText())
        }

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
        val asadmin = findViewById<TextView>(R.id.asadmin)
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
            }else if (TextUtils.isEmpty(asadmin.text.toString())) {
                asadmin.setError("Please confirm position")
                return@setOnClickListener
            }

            authAdmin.createUserWithEmailAndPassword(emailaddress.text.toString(), adminpass.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUserAdmin = authAdmin.currentUser
                        val currentUserAdminDB = databaseReferenceAdmin?.child((currentUserAdmin?.uid!!))
                        currentUserAdmin.sendEmailVerification().addOnCompleteListener {
                            if(it.isSuccessful){
                                currentUserAdminDB?.child("As")?.setValue(asadmin.text.toString())
                                currentUserAdminDB?.child("AdminID")?.setValue(adminID.text.toString())
                                currentUserAdminDB?.child("AdminName")?.setValue(adminName.text.toString())
                                currentUserAdminDB?.child("EmailAddress")?.setValue(emailaddress.text.toString())
                                currentUserAdminDB?.child("SecuPass")?.setValue(adminpass.text.toString())

                                Toast.makeText(this, "Registration succes! Email verification sent!", Toast.LENGTH_LONG).show()
                                finish()
                            }else{
                                Toast.makeText(
                                        this,
                                        "Registration Failed, try agian!",
                                        Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    }else {
                        Toast.makeText(
                                this,
                                "Registration Failed, try agian!",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        backbtn.setOnClickListener{
            startActivity(Intent(this, Administrators::class.java))
        }
    }

    fun checkButton(v: View?) {
        val radioId: Int = radioGroup!!.getCheckedRadioButtonId()
        radioButton = findViewById<RadioButton>(radioId)
        Toast.makeText(this, "Selected Radio Button: " + radioButton?.getText(),
                Toast.LENGTH_SHORT).show()
    }
}
