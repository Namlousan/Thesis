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

public class Adminapphistory1 extends AppCompatActivity {

    RecyclerView recyclerView;
    admintablelogsadapter adminadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminapphistory1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        recyclerView = findViewById(R.id.clientlisthistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<admintablelogs> options =
                new FirebaseRecyclerOptions.Builder<admintablelogs>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("AdminLogs"), admintablelogs.class)
                        .build();

        adminadapter = new admintablelogsadapter(options);
        recyclerView.setAdapter(adminadapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adminadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adminadapter.stopListening();
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
                assearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textsearch(query);
                assearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void textsearch(String str) {
        FirebaseRecyclerOptions<admintablelogs> options =
                new FirebaseRecyclerOptions.Builder<admintablelogs>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("AdminLogs").orderByChild("ID").startAt(str).endAt(str+"~"), admintablelogs.class)
                        .build();

        adminadapter = new admintablelogsadapter(options);
        adminadapter.startListening();
        recyclerView.setAdapter(adminadapter);
    }

    private void assearch(String str) {
        FirebaseRecyclerOptions<admintablelogs> options =
                new FirebaseRecyclerOptions.Builder<admintablelogs>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("AdminLogs").orderByChild("As").startAt(str).endAt(str+"~"), admintablelogs.class)
                        .build();

        adminadapter = new admintablelogsadapter(options);
        adminadapter.startListening();
        recyclerView.setAdapter(adminadapter);
    }
}