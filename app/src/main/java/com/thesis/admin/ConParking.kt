package com.thesis.admin

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ConParking : AppCompatActivity() {

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_parking)

        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("zParkingDb").child("CoN Area").child("Slot1")
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val stat = snapshot.child("status").value.toString()
                val rootRef = FirebaseDatabase.getInstance().reference
                val slot13 = findViewById<RelativeLayout>(R.id.slot13)

                slot13.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        if (stat.equals("avail")) {
                            rootRef.child("zParkingDb").child("CoN Area").child("Slot1").child("status").setValue("occupied")
                        } else {
                            rootRef.child("zParkingDb").child("CoN Area").child("Slot1").child("status").setValue("avail")
                        }
                    }
                })
                val button = findViewById<Button>(R.id.backimg)
                button.setOnClickListener {
                    startActivity(Intent(this@ConParking, Security::class.java))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        onchange()
    }

    fun onchange() {
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("zParkingDb").child("CoN Area").child("Slot1")
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stat = snapshot.child("status").value.toString()
                val asss = findViewById<TextView>(R.id.asss)
                //val rectangle_red = ContextCompat.getDrawable(this@ConParking, R.drawable.rectangle_red)
                if (stat.equals("avail")) {
                        asss.setBackgroundColor(Color.GREEN)
                }else if(stat.equals("occupied")){
                    asss.setBackgroundColor(Color.RED)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}




