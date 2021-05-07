package com.thesis.admin

import android.annotation.TargetApi
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

class Home_menu : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_menu)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();
        val cardView = findViewById<CardView>(R.id.client)
        cardView.setOnClickListener {
            val intent = Intent(this, Client::class.java)
            startActivity(intent)
        }


            val secure = findViewById<CardView>(R.id.security1)
            secure.setOnClickListener {
                val intent = Intent(this, Security::class.java)
                startActivity(intent)
            }

        val cardViewlogout=findViewById<CardView>(R.id.logout)
        cardViewlogout.setOnClickListener {
            val alertdialog: AlertDialog = AlertDialog.Builder(this).create()
            alertdialog.setTitle("Log out")
            alertdialog.setMessage("Are you sure you want to logout?")
            alertdialog.setButton(AlertDialog.BUTTON_POSITIVE,"yes"){
                dialog, which ->  finishAffinity();
                System.exit(0);
                dialog.dismiss()
            }
            alertdialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No"){
                dialog, which ->
                dialog.dismiss()
            }
            alertdialog.show()
        }
        }
            }

