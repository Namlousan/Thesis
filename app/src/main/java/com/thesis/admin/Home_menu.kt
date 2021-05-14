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
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class Home_menu : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {

        authAdmin = FirebaseAuth.getInstance()

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

        val appmonitor = findViewById<CardView>(R.id.Appmoni)
        appmonitor.setOnClickListener {
            val intent = Intent(this, Administrators::class.java)
            startActivity(intent)
        }

        val parking = findViewById<CardView>(R.id.parkinglots)
        parking.setOnClickListener {
            val intent = Intent(this, ParkingLots::class.java)
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
                dialog, which ->  authAdmin.signOut();
                startActivity(Intent(this@Home_menu, MainActivity::class.java))
                finish()
            }
            alertdialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No"){
                dialog, which ->
                dialog.dismiss()
            }
            alertdialog.show()
        }
        }
            }

