package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.apache.commons.lang3.RandomStringUtils

class NewClientUI : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var databaseReference : DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_client_ui)


        val randonString: String = RandomStringUtils.randomAlphanumeric(9).toUpperCase()
        val textView = findViewById<TextView>(R.id.clientcode)
        textView.setText("FOMICS" + randonString)



        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("ClientDb")




      /*  val button2 = findViewById<Button>(R.id.save)
        button2.setOnClickListener {
            val code = findViewById<TextView>(R.id.clientcode)
            val intent = Intent(this, Save_client::class.java)
            intent.putExtra("result", code.getText());
           startActivity(intent)
        }*/
        register()
    }
    private fun register(){

        val regButton = findViewById<Button>(R.id.save)
        val backtoclientmenu = findViewById<ImageButton>(R.id.backbutton)
        val branch = findViewById<TextInputEditText>(R.id.branch)
        val empid = findViewById<TextInputEditText>(R.id.empid)
        val deptartment = findViewById<TextInputEditText>(R.id.department)
        val depcode = findViewById<TextInputEditText>(R.id.Dcode)
        val lname = findViewById<TextInputEditText>(R.id.lastname)
        val fname = findViewById<TextInputEditText>(R.id.firstname)
        val email = findViewById<TextInputEditText>(R.id.email)
        val pass = findViewById<TextInputEditText>(R.id.accesscode)
        val textView =findViewById<TextView>(R.id.clientcode)
        regButton.setOnClickListener{

            if (TextUtils.isEmpty(branch.text.toString())){
                branch.setError("Please enter your last name")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(empid.text.toString())) {
                empid.setError("Please enter your last name")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(deptartment.text.toString())) {
                deptartment.setError("Please enter your email")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(depcode.text.toString())) {
                depcode.setError("Please enter your ID number")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(lname.text.toString())) {
                lname.setError("Please enter your ID number")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(fname.text.toString())) {
                fname.setError("Please enter your ID number")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(email.text.toString())) {
                email.setError("Please enter your ID number")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(pass.text.toString())) {
                pass.setError("Please enter your ID number")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email.text.toString(),pass.text.toString() )
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("branch")?.setValue(branch.text.toString())
                        currentUserDb?.child("empid")?.setValue(empid.text.toString())
                        currentUserDb?.child("department")?.setValue(deptartment.text.toString())
                        currentUserDb?.child("deptcode")?.setValue(depcode.text.toString())
                        currentUserDb?.child("lastname")?.setValue(lname.text.toString())
                        currentUserDb?.child("firtname")?.setValue(fname.text.toString())
                        currentUserDb?.child("email")?.setValue(email.text.toString())
                        currentUserDb?.child("clientcode")?.setValue(textView.text.toString())
                        currentUserDb?.child("password")?.setValue(pass.text.toString())
                        Toast.makeText(this@NewClientUI, "Registration success!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, Save_client::class.java)
                        intent.putExtra("result", textView.getText())
                        startActivity(intent)
                        // finish()
                    } else {
                        Toast.makeText(
                            this@NewClientUI,
                            "Registration Failed, try again!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
        backtoclientmenu.setOnClickListener{
            startActivity(Intent(this@NewClientUI, Home_menu::class.java))
        }
    }

}

