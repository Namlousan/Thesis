package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class ParkingLOGS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_logs)

        val cardview1 = findViewById<CardView>(R.id.parkingdb)
        cardview1.setOnClickListener {
            val intent = Intent(this, ParkinglotDB::class.java)
            startActivity(intent)
        }

        val cardview2 = findViewById<CardView>(R.id.logs)
        cardview2.setOnClickListener {
            val intent = Intent(this, logparking::class.java)
            startActivity(intent)
        }
        val cardview3 = findViewById<CardView>(R.id.history)
        cardview3.setOnClickListener {
            val intent = Intent(this, history::class.java)
            startActivity(intent)
        }
    }

}