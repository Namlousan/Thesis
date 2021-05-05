package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView

class Manuals : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manuals)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();


        val cardView = findViewById<CardView>(R.id.clientmanual)
        cardView.setOnClickListener {
            val intent = Intent(this, Clientmanual::class.java)
            startActivity(intent)
    }
        val cardView2 = findViewById<CardView>(R.id.backtosecurity)
        cardView2.setOnClickListener {
            val intent = Intent(this, Security::class.java)
            startActivity(intent)
        }
}}
