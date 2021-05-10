package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Heroes_park : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes_park)

        val button = findViewById<Button>(R.id.backimg)
        button.setOnClickListener{
            val intent = Intent(this, ParkingLots::class.java)
            startActivity(intent)
        }
    }
}