package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class NewClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_client)


        val button = findViewById<Button>(R.id.backtoclientmenu)
        button.setOnClickListener{
            val intent = Intent(this, Client::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.saveclient)
        button2.setOnClickListener{
            val intent = Intent(this, Save_client::class.java)
            startActivity(intent)
        }

        val button3 = findViewById<Button>(R.id.updateclient)
        button3.setOnClickListener{
            val intent = Intent(this, U_client_Info::class.java)
            startActivity(intent)
        }
    }
}