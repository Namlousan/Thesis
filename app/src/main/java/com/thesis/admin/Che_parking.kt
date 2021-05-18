package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Che_parking : AppCompatActivity() {



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_che_parking)



        val slot1 = findViewById<TextView>(R.id.myImageViewText)
        slot1.setOnClickListener{
            slot1.text = "Occupied"
        }

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();

        val button = findViewById<Button>(R.id.backimg)
        button.setOnClickListener{
            val intent = Intent(this, ParkingLots::class.java)
            startActivity(intent)
        }
    }
}