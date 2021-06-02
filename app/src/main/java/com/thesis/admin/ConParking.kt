package com.thesis.admin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ConParking : AppCompatActivity() {

    var databaseReferenceAdmin : DatabaseReference? = null
    var databaseAdmin: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_parking)


        databaseAdmin = FirebaseDatabase.getInstance()
        databaseReferenceAdmin = databaseAdmin?.reference!!.child("zParkingDb").child("CoN Area").child("Slot1")
        databaseReferenceAdmin?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stat = snapshot.child("status").value.toString()

                val rootRef = FirebaseDatabase.getInstance().reference
                val slot13 = findViewById<RelativeLayout>(R.id.slot13)
                val asss = findViewById<TextView>(R.id.asss)


                slot13.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View?) {

                        if (stat.equals("avail")) {
                            rootRef.child("zParkingDb").child("CoN Area").child("Slot1").child("status").setValue("occupied")
                            asss.setBackgroundColor(Color.RED)
                        } else {
                            rootRef.child("zParkingDb").child("CoN Area").child("Slot1").child("status").setValue("avail")
                            asss.setBackgroundColor(Color.GREEN)
                        }
                    }
                })


                val button = findViewById<Button>(R.id.backimg)
                button.setOnClickListener {
                    startActivity(Intent(this@ConParking, parking_fragment::class.java))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}

