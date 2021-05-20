package com.thesis.admin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.apache.commons.lang3.RandomStringUtils

class RegiterNewClient : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regiter_new_client)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("ClientDb")


        register()
    }

    private fun register(){
        val asclient = findViewById<TextView>(R.id.asclient)
        val regButton = findViewById<Button>(R.id.saveclient)
        val backtoclientmenu = findViewById<Button>(R.id.backtoclientmenu)
        val branch = findViewById<EditText>(R.id.branch)
        val empid = findViewById<EditText>(R.id.empid)
        val deptartment = findViewById<EditText>(R.id.deptartment)
        val depcode = findViewById<EditText>(R.id.depcode)
        val lname = findViewById<EditText>(R.id.lname)
        val fname = findViewById<EditText>(R.id.fname)
        val email = findViewById<EditText>(R.id.email)
        val pass = findViewById<EditText>(R.id.pass)
        val platenum = findViewById<EditText>(R.id.platenum)
        val brand = findViewById<EditText>(R.id.brand)
        val colorvehicle = findViewById<EditText>(R.id.colorvehicle)


        regButton.setOnClickListener{

            if (TextUtils.isEmpty(branch.text.toString())){
                branch.setError("Please enter branch")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(empid.text.toString())) {
                empid.setError("Please enter Employee ID")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(deptartment.text.toString())) {
                deptartment.setError("Please enter College")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(depcode.text.toString())) {
                depcode.setError("Please enter departmet code")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(lname.text.toString())) {
                lname.setError("Please enter client's last name")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(fname.text.toString())) {
                fname.setError("Please enter client's lirst Name")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(email.text.toString())) {
                email.setError("Please enter valid email")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(pass.text.toString())) {
                pass.setError("Please enter access code")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(platenum.text.toString())) {
                platenum.setError("Please enter PL Number")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(brand.text.toString())) {
                brand.setError("Please enter Vehicle Brand")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(colorvehicle.text.toString())) {
                colorvehicle.setError("Please Vehicle Color")
                return@setOnClickListener
            }




            auth.createUserWithEmailAndPassword(email.text.toString(),pass.text.toString() )
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("as")?.setValue(asclient.text.toString())
                        currentUserDb?.child("branch")?.setValue(branch.text.toString())
                        currentUserDb?.child("empid")?.setValue(empid.text.toString())
                        currentUserDb?.child("department")?.setValue(deptartment.text.toString())
                        currentUserDb?.child("deptcode")?.setValue(depcode.text.toString())
                        currentUserDb?.child("lastname")?.setValue(lname.text.toString())
                        currentUserDb?.child("firtname")?.setValue(fname.text.toString())
                        currentUserDb?.child("email")?.setValue(email.text.toString())
                        currentUserDb?.child("password")?.setValue(pass.text.toString())
                        currentUserDb?.child("plateNumber")?.setValue(platenum.text.toString())
                        currentUserDb?.child("vehicleBrand")?.setValue(brand.text.toString())
                        currentUserDb?.child("vehicleColor")?.setValue(colorvehicle.text.toString())

                        Toast.makeText(this@RegiterNewClient, "Registration succes!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegiterNewClient,
                            "Registration Failed, try agian!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
        backtoclientmenu.setOnClickListener{
            startActivity(Intent(this@RegiterNewClient, Home_menu::class.java))
        }
    }
}