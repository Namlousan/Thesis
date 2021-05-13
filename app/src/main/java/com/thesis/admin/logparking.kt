package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class logparking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logparking)
        val button = findViewById<Button>(R.id.back)
        button.setOnClickListener{
            val intent = Intent(this, ParkingLOGS::class.java)
            startActivity(intent)
        }
    }
}