package com.thesis.admin

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.apache.commons.lang3.RandomStringUtils

class U_client_Info : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u_client__info)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar()?.hide();
        val button = findViewById<Button>(R.id.clearfields)
        val edittext2 =findViewById<EditText>(R.id.branch)
        val edittext3 =findViewById<EditText>(R.id.emp_id)
        val edittext4 =findViewById<EditText>(R.id.dept)
        val edittext5 =findViewById<EditText>(R.id.dept_code)
        val edittext6 =findViewById<EditText>(R.id.last_name)
        val edittext7 =findViewById<EditText>(R.id.first_name)
        val edittext8 =findViewById<EditText>(R.id.access_name)
        val edittext9 =findViewById<EditText>(R.id.access_code)
        val edittext10 =findViewById<EditText>(R.id.plate_number)
        val edittext11 =findViewById<EditText>(R.id.model)
        val edittext12 =findViewById<EditText>(R.id.color)
        button.setOnClickListener {
            edittext2.setText("")
            edittext3.setText("")
            edittext4.setText("")
            edittext5.setText("")
            edittext6.setText("")
            edittext7.setText("")
            edittext8.setText("")
            edittext9.setText("")
            edittext10.setText("")
            edittext11.setText("")
            edittext12.setText("")
        }
        val button1 = findViewById<Button>(R.id.saveclient)
        button1.setOnClickListener {
            val intent = Intent(this, Save_client::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<Button>(R.id.backtoclientmenu)
        button3.setOnClickListener{
            val intent = Intent(this, Client::class.java)
            startActivity(intent)


    }
        val randonString: String = RandomStringUtils.randomAlphanumeric(9)
        val textView =findViewById<TextView>(R.id.clientcode)
        textView.setText("FOMICS"+randonString)
    }}