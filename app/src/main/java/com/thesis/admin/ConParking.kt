package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ConParking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_parking)


        val button = findViewById<Button>(R.id.backimg)
        button.setOnClickListener{
            val intent = Intent(this, ParkingLots::class.java)
            startActivity(intent)
        }
    }
}