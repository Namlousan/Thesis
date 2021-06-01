package com.thesis.admin;

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.thesis.admin.data.Clients

class Update_client : AppCompatActivity() {

private lateinit var dbref :DatabaseReference
private lateinit var clienthistory : RecyclerView
private lateinit var clientArrayList : ArrayList<Clients>
private lateinit var clientupdateadapter: Clientupdateadapter

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_update_client)
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )



            clienthistory = findViewById(R.id.clientlisthistory)
            clienthistory.layoutManager = LinearLayoutManager(this)

            loadProfile()
            }

private fun loadProfile() {

 //       clientArrayList = arrayListOf()
//        dbref = FirebaseDatabase.getInstance().getReference("ClientDb")
//        dbref.addValueEventListener(object : ValueEventListener {
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                        if (snapshot.exists()) {
//                                for (userSnapshot in snapshot.children) {
//                                        val users = userSnapshot.getValue(Clients::class.java)
//                                        clientArrayList.add(users!!)
//                                }
//                        }
//                        clienthistory.adapter = Clientupdateadapter(clientArrayList)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                        TODO("Not yet implemented")
//                }
//
//        })
        val options: FirebaseRecyclerOptions<Clients> = FirebaseRecyclerOptions.Builder<Clients>()
                .setQuery(FirebaseDatabase.getInstance().getReference("ClientDb"), Clients::class.java)
                .build()

        clientupdateadapter = Clientupdateadapter(options)
        clienthistory.setAdapter(clientupdateadapter)

        }
        override fun onStart() {
                super.onStart()
                clientupdateadapter.startListening()
        }
        override fun onStop() {
                super.onStop()
                clientupdateadapter.stopListening()
        }

//        override fun onCreateOptionsMenu(menu: Menu): Boolean {
//                menuInflater.inflate(R.menu.search, menu)
//                val item: MenuItem = menu.findItem(R.id.search)
//                val searchView = item.actionView as SearchView
//                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                        override fun onQueryTextSubmit(query: String): Boolean {
//                                        textSearch(query)
//                                return false
//                        }
//
//                        override fun onQueryTextChange(query: String): Boolean {
//                                         textSearch(query)
//                                return false
//                        }
//                })
//                return super.onCreateOptionsMenu(menu)
//        }

        private fun textSearch(str: String) {
                val options = FirebaseRecyclerOptions.Builder<Clients>()
                        .setQuery(
                                FirebaseDatabase.getInstance().getReference("ClientDb")
                                        .orderByChild("firtname").startAt(str).endAt(
                                                str+"~"
                                        ),
                                Clients::class.java
                        )
                        .build()

                clientupdateadapter = Clientupdateadapter(options)
                clientupdateadapter.startListening()
                clienthistory.setAdapter(clientupdateadapter)
        }


}





