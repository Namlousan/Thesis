package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView

class Tables_1 : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables_1)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();

        val cardView = findViewById<CardView>(R.id.clientApp)
        cardView.setOnClickListener {
            val intent = Intent(this, Client_history_table::class.java)
            startActivity(intent)
        }
        val cardView2 = findViewById<CardView>(R.id.AdminApp)
        cardView2.setOnClickListener {
            val intent = Intent(this, Adminapphistory1::class.java)
            startActivity(intent)
        }
        val cardView3 = findViewById<CardView>(R.id.secuApp)
        cardView3.setOnClickListener {
            val intent = Intent(this, SecurityApphistory::class.java)
            startActivity(intent)
        }
        val cardView4 = findViewById<CardView>(R.id.backtohomeH)
        cardView4.setOnClickListener {
            val intent = Intent(this, Home_menu::class.java)
            startActivity(intent)
        }
    }
}