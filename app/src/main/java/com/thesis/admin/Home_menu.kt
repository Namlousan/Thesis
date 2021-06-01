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
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class Home_menu : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null


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
        val appmoni = findViewById<CardView>(R.id.Appmoni)
        appmoni.setOnClickListener {
            val intent = Intent(this, Tables_1::class.java)
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

        val cardViewlogout = findViewById<CardView>(R.id.logout)
        cardViewlogout.setOnClickListener {
            val alertdialog: AlertDialog = AlertDialog.Builder(this).create()
            alertdialog.setTitle("Log out")
            alertdialog.setMessage("Are you sure you want to logout?")
            alertdialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes") { dialog, which ->

                auth = FirebaseAuth.getInstance()
                database = FirebaseDatabase.getInstance()
                databaseReference = database?.reference!!.child("SecuDb")

                val user = auth.currentUser
                val userreference = databaseReference?.child(user?.uid!!)

                userreference?.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val name = snapshot.child("AdminName").value.toString()
                        val id = snapshot.child("AdminID").value.toString()
                        val As = snapshot.child("As").value.toString()
                        val df = SimpleDateFormat("EEE MM/dd/yyyy hh:mm.ss aa")
                        val c = Calendar.getInstance()
                        val str_time: String = df.format(c.time)

                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("AdminLogs")
                        val map: HashMap<String, String?> = hashMapOf(
                                "Name" to name,
                                "Date" to str_time,
                                "As" to As,
                                "ID" to id,
                                "LogStat" to "LogOut"
                        )
                        myRef.push().setValue(map)
                        auth.signOut()
                        startActivity(Intent(this@Home_menu, MainActivity::class.java))
                        finish()

                        alertdialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No")
                        { dialog, which ->
                            dialog.dismiss()
                        }
                        alertdialog.show()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }
}

