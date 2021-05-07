package com.thesis.admin

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Clientmanual : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientmanual)

        val textView =findViewById<TextView>(R.id.how_to_do)
        textView.movementMethod = ScrollingMovementMethod()


        val button = findViewById<Button>(R.id.back2)
        button.setOnClickListener{
            val intent = Intent(this, Security::class.java)
            startActivity(intent)
        }

    }
}