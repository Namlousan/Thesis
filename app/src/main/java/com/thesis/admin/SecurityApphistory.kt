package com.thesis.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecurityApphistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_apphistory)
        val button =findViewById<Button>(R.id.backtohome)
        button.setOnClickListener {
            val intent = Intent(this, Tables_1::class.java)
            startActivity(intent)
        }
    }
}