package com.thesis.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Update_client extends AppCompatActivity {


    TextInputLayout branch_spinner;
    AutoCompleteTextView act_branch;

    ArrayList<String> arrayList_bulsubranch;
    ArrayAdapter<String> arrayAdapter_bulsubranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);


        branch_spinner=(TextInputLayout)findViewById(R.id.branch_dropdown);
        act_branch=(AutoCompleteTextView)findViewById(R.id.act_branch);
        arrayList_bulsubranch= new ArrayList<>();
        arrayList_bulsubranch.add("Main campus");
        arrayList_bulsubranch.add("Sarmiento campus");
        arrayList_bulsubranch.add("Bustos campus");
        arrayList_bulsubranch.add("Meneses campus");
        arrayList_bulsubranch.add("Hagonoy campus");


        arrayAdapter_bulsubranch= new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arrayList_bulsubranch);
        act_branch.setAdapter(arrayAdapter_bulsubranch);
        act_branch.setThreshold(1);
        Button btn = (Button)findViewById(R.id.back_to_client);

        btn.setOnClickListener(v -> startActivity(new Intent(Update_client.this, Home_menu.class)));}}