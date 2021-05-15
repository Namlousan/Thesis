package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth



class Home_menu : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth

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
                dialog, which ->  finishAffinity();
                FirebaseAuth.getInstance().signOut()
               val intent =Intent(this, MainActivity::class.java)
                startActivity(intent)
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

