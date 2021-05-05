package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class Save_client : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_client)

        val button = findViewById<Button>(R.id.QRback)
        button.setOnClickListener{
            val intent = Intent(this, Client::class.java)
            startActivity(intent)
        }
    }
}