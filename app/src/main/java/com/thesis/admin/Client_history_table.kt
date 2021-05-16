package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Client_history_table : AppCompatActivity() {
    private lateinit var dbref :DatabaseReference
    private lateinit var clienthistory : RecyclerView
    private lateinit var clientArrayList : ArrayList<User>
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_history_table)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar()?.hide();


        //buttons
        val button =findViewById<Button>(R.id.backtohome)
        button.setOnClickListener {
            val intent = Intent(this, Tables_1::class.java)
            startActivity(intent)
        }


        clienthistory = findViewById(R.id.clientlisthistory)
        clienthistory.layoutManager = LinearLayoutManager(this,)
        clienthistory.setHasFixedSize(true)


        clientArrayList = arrayListOf()
        getUserData()


    }
    private fun getUserData(){
        dbref = FirebaseDatabase.getInstance().getReference("ClientDb")
        dbref.addValueEventListener(object :ValueEventListener{


            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(User::class.java)
                        clientArrayList.add(user!!)
                    }
                }
                clienthistory.adapter =CHAdapter(clientArrayList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



}