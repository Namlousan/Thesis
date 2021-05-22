package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Administrators : AppCompatActivity() {

    private lateinit var dbref :DatabaseReference
    private lateinit var clienthistory : RecyclerView
    private lateinit var clientArrayList : ArrayList<Adminis>
    private lateinit var clientsearch: AutoCompleteTextView

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrators)
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar()?.hide();


        clienthistory = findViewById(R.id.clientlisthistory)
        clienthistory.layoutManager = LinearLayoutManager(this,)
        clienthistory.setHasFixedSize(true)
        clientArrayList = arrayListOf()


        loadProfile()
    }

        private fun loadProfile() {

            clientArrayList = arrayListOf()
                dbref = FirebaseDatabase.getInstance().getReference("AdminDB")
                dbref.addValueEventListener(object :ValueEventListener{


                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            for(userSnapshot in snapshot.children){
                                val users = userSnapshot.getValue(Adminis::class.java)
                                clientArrayList.add(users!!)
                            }
                        }
                        clienthistory.adapter =adminadapter(clientArrayList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })


            val back1 = findViewById<TextView>(R.id.backtohome)
            back1.setOnClickListener {
                startActivity(Intent(this@Administrators, Home_menu::class.java))
                finish()
            }
            }
        }


