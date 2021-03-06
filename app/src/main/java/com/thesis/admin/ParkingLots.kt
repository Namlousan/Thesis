package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView

class ParkingLots : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_lots)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();

        val cardView = findViewById<CardView>(R.id.backtohome)
        cardView.setOnClickListener {
            val intent = Intent(this, Home_menu::class.java)
            startActivity(intent)
        }

        val law = findViewById<CardView>(R.id.claw)
        law.setOnClickListener {
            val intent = Intent(this, parking_law::class.java)
            startActivity(intent)
        }

        val con = findViewById<CardView>(R.id.cn)
        con.setOnClickListener {
            val intent = Intent(this, ConParking::class.java)
            startActivity(intent)
        }
        val hp = findViewById<CardView>(R.id.hp)
        hp.setOnClickListener {
            val intent = Intent(this, Heroes_park::class.java)
            startActivity(intent)
        }
        val che = findViewById<CardView>(R.id.che)
        che.setOnClickListener {
            val intent = Intent(this, Che_parking::class.java)
            startActivity(intent)
        }
        val PL = findViewById<CardView>(R.id.parkinglogs)
        PL.setOnClickListener {
            val intent = Intent(this, ParkingLOGS::class.java)
            startActivity(intent)
        }
    }
}