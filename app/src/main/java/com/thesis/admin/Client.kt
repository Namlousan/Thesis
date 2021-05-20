package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView

class Client : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();


        val cardView1 = findViewById<CardView>(R.id.new_client)
        cardView1.setOnClickListener {
            startActivity(Intent(this@Client, NewClient::class.java))
        }

            val cardView2 = findViewById<CardView>(R.id.update_client)
            cardView2.setOnClickListener {
                val intent = Intent(this, Update_client::class.java)
                startActivity(intent)
            }


                val cardView3 = findViewById<CardView>(R.id.clientstng)
                cardView3.setOnClickListener{
                    val intent = Intent(this, Client_settings::class.java)
                    startActivity(intent)


                }


        val cardView4 = findViewById<CardView>(R.id.backtoadmin)
        cardView4.setOnClickListener {
            val intent = Intent(this, Home_menu::class.java)
            startActivity(intent)
        }
        val cardView5 = findViewById<CardView>(R.id.logs)
        cardView5.setOnClickListener {
            val intent = Intent(this, Logs::class.java)
            startActivity(intent)
        }

        }}