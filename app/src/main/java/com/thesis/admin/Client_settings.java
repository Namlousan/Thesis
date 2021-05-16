package com.thesis.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Client_settings extends AppCompatActivity {
    TextInputLayout choose_client;
    AutoCompleteTextView act_client;
    RadioGroup radioGroup;
    RadioButton radioButton;

    DatabaseReference ref;



    ArrayList<String> arrayList_chooseclient;
    ArrayAdapter<String> arrayAdapter_chooseclient;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ref = FirebaseDatabase.getInstance().getReference("ClientDb");


        // for combo box
        choose_client=findViewById(R.id.choose_client);
        act_client=findViewById(R.id.act_client);
        arrayList_chooseclient= new ArrayList<>();
        arrayList_chooseclient.add("Nayeon");
        arrayList_chooseclient.add("Dahyun");
        arrayList_chooseclient.add("Chaeyoung");
        arrayList_chooseclient.add("Sana");
        arrayList_chooseclient.add("Jihyo");
        arrayAdapter_chooseclient= new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arrayList_chooseclient);
        act_client.setAdapter(arrayAdapter_chooseclient);
        act_client.setThreshold(1);



        //radio groups

        radioGroup = findViewById(R.id.selection);
        Button buttonsave = findViewById(R.id.save);


        Button btn = (Button)findViewById(R.id.cancel);

        btn.setOnClickListener(v -> startActivity(new Intent(Client_settings.this, Client.class)));}
    }
