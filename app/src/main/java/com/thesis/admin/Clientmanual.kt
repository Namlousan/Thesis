package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class Clientmanual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientmanual)

        

        val button = findViewById<Button>(R.id.back2)
        button.setOnClickListener{
            val intent = Intent(this, Security::class.java)
            startActivity(intent)
        }

    }
}