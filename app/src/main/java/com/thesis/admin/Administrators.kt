package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class Administrators : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrators)

        val button = findViewById<Button>(R.id.back1)
        button.setOnClickListener{
            val intent = Intent(this, Security::class.java)
            startActivity(intent)
        }
    }
}