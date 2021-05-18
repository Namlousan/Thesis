package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Administrators : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth
    var databaseReferenceAdmin : DatabaseReference? = null
    var databaseAdmin: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrators)

        authAdmin = FirebaseAuth.getInstance()
        databaseAdmin = FirebaseDatabase.getInstance()
        databaseReferenceAdmin = databaseAdmin?.reference!!.child("AdminDB")



        loadProfile()
    }

        private fun loadProfile() {

            val useradminID = findViewById<TextView>(R.id.useradminID)
            val useradminName = findViewById<TextView>(R.id.useradminName)
            val deptcode = findViewById<TextView>(R.id.deptcode)

            val user = authAdmin.currentUser
            val userreference = databaseReferenceAdmin?.child(user?.uid!!)


            userreference?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    useradminID.text = snapshot.child("AdminID").value.toString()
                    useradminName.text = snapshot.child("AdminName").value.toString()
                    deptcode.text = snapshot.child("AdminDeptCode").value.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

            val back1 = findViewById<TextView>(R.id.back1)
            back1.setOnClickListener {
                startActivity(Intent(this@Administrators, Home_menu::class.java))
                finish()
            }

            val back11 = findViewById<TextView>(R.id.back11)
            back11.setOnClickListener {
                startActivity(Intent(this@Administrators, New_admin::class.java))
                finish()
            }
        }
    }
