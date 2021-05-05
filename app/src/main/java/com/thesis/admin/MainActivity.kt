package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();

        val button = findViewById<Button>(R.id.login)
        button.setOnClickListener{
            val intent = Intent(this, Home_menu::class.java)
            startActivity(intent)
        }

        val button2 =findViewById<Button>(R.id.cancel_mainscreen)
        button2.setOnClickListener {
            finish()
        }


}}