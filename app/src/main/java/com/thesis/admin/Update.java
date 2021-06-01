package com.thesis.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.thesis.admin.data.Clients;

public class Update extends AppCompatActivity {

    RecyclerView recyclerView;
    Clientupdateadapter clientupdateadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.clientlisthistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Clients> options =
                new FirebaseRecyclerOptions.Builder<Clients>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("ClientDb"), Clients.class)
                        .build();

        clientupdateadapter = new Clientupdateadapter(options);
        recyclerView.setAdapter(clientupdateadapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        clientupdateadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clientupdateadapter.stopListening();
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.searchbar);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                textsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textsearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void textsearch(String str) {
        FirebaseRecyclerOptions<Clients> options =
                new FirebaseRecyclerOptions.Builder<Clients>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("ClientDb").orderByChild("firtname").startAt(str).endAt(str+"~"), Clients.class)
                        .build();

        clientupdateadapter = new Clientupdateadapter(options);
        clientupdateadapter.startListening();
        recyclerView.setAdapter(clientupdateadapter);
    }
}