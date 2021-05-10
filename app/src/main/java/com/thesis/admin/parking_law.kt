package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class parking_law : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_law)

        val button = findViewById<Button>(R.id.backimg)
        button.setOnClickListener{
            val intent = Intent(this, ParkingLots::class.java)
            startActivity(intent)
        }

}}