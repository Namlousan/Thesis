package com.thesis.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Logs extends AppCompatActivity {
    TextInputLayout choose_client;
    AutoCompleteTextView act_client;
    ArrayList<String> arrayList_chooseclient;
    ArrayAdapter<String> arrayAdapter_chooseclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        //combo box
        choose_client=(TextInputLayout)findViewById(R.id.choose_client);
        act_client=(AutoCompleteTextView)findViewById(R.id.act_client);
        arrayList_chooseclient= new ArrayList<>();
        arrayList_chooseclient.add("Nayeon");
        arrayList_chooseclient.add("Dahyun");
        arrayList_chooseclient.add("Chaeyoung");
        arrayList_chooseclient.add("Sana");
        arrayList_chooseclient.add("Jihyo");
        arrayAdapter_chooseclient= new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arrayList_chooseclient);
        act_client.setAdapter(arrayAdapter_chooseclient);
        act_client.setThreshold(1);

        Button btn = (Button)findViewById(R.id.back_toclientlogs);

        btn.setOnClickListener(v -> {
            startActivity(new Intent(Logs.this, Client.class));
        });}
    }
