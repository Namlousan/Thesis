package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.thesis.admin.data.Clients


class Client_settings : AppCompatActivity() {

private lateinit var dbref :DatabaseReference
private lateinit var clienthistory : RecyclerView
private lateinit var clientArrayList : ArrayList<Clients>
private lateinit var clientsearch: AutoCompleteTextView
        var ref: DatabaseReference? = null

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_client_settings)
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            getSupportActionBar()?.hide();
        search()

            clienthistory = findViewById(R.id.clientlisthistory)
            clienthistory.layoutManager = LinearLayoutManager(this)
            clienthistory.setHasFixedSize(true)
            clientArrayList = arrayListOf()

            loadProfile()

            }
                private fun search(){

                        ref = FirebaseDatabase.getInstance().reference
                        val autocomplete = ArrayAdapter<String>(this@Client_settings, android.R.layout.simple_list_item_1)
                        ref!!.child("ClientDb").addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        for (suggestionSnapshot in dataSnapshot.children) {
                                                val firtname = suggestionSnapshot.child("firtname").getValue(
                                                        String::class.java
                                                )
                                                autocomplete.add(firtname)
                                        }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {}
                        })
                        val ACTV = findViewById<View>(R.id.act_client) as AutoCompleteTextView
                        ACTV.setAdapter(autocomplete)
                }

private fun loadProfile() {

        clientArrayList = arrayListOf()
        dbref = FirebaseDatabase.getInstance().getReference("ClientDb")
        dbref.addValueEventListener(object : ValueEventListener {


                override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                                for (userSnapshot in snapshot.children) {
                                        val users = userSnapshot.getValue(Clients::class.java)
                                        clientArrayList.add(users!!)
                                }
                        }
                        clienthistory.adapter = Clientadaptor(clientArrayList)
                }

                override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                }

        })


        val back1 = findViewById<TextView>(R.id.cancel)
        back1.setOnClickListener {
        startActivity(Intent(this@Client_settings, Home_menu::class.java))
        finish()
        }
        }
        }

