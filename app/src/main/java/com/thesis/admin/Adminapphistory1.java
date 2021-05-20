package com.thesis.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adminapphistory1 extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<Admins>list;
     RecyclerView result;
    SearchView searchView;
    private AutoCompleteTextView search_admin;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminapphistory1);
       ref = FirebaseDatabase.getInstance().getReference().child("AdminDB");
       result = findViewById(R.id.adminresult);
       searchView = findViewById(R.id.search);
       Button btn =findViewById(R.id.backtohome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    //combo box search
       /* ArrayAdapter<String> autocomplete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        ref.child("AdminDB").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()) {

                    String firtname = suggestionSnapshot.child("AdminName").getValue(String.class);
                    autocomplete.add(firtname);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        AutoCompleteTextView ACTV = (AutoCompleteTextView) findViewById(R.id.act_admin1);
        ACTV.setAdapter(autocomplete);*/
        btn.setOnClickListener(v -> {
            startActivity(new Intent(Adminapphistory1.this, Home_menu.class));
        });
    }
        @Override
    protected void onStart(){
        super.onStart();
        if (ref!= null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        list = new ArrayList<>();
                        for(DataSnapshot ds: snapshot.getChildren())
                        {
                        list.add(ds.getValue(Admins.class));
                        }
                    adminadapter adminadapter = new adminadapter(list);
                        result.setAdapter(adminadapter);

                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }

        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }

                }
            private void search(String str) {
                ArrayList<Admins> myList =  new ArrayList<>();
                for (Admins object :list){
                    if (object.getAdminName().toLowerCase().contains(str.toLowerCase()))
                    {
                        myList.add(object);
                    }
                }
           adminadapter adminadapter = new adminadapter(myList);
                result.setAdapter(adminadapter);


            }

}











