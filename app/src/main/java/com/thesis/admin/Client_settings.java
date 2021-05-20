package com.thesis.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Client_settings extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;

    private RadioButton radio1, radio2;

    DatabaseReference ref;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ref = FirebaseDatabase.getInstance().getReference();
        ArrayAdapter<String> autocomplete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        ref.child("ClientDb").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()) {

                    String firtname = suggestionSnapshot.child("firtname").getValue(String.class);
                    autocomplete.add(firtname);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        AutoCompleteTextView ACTV = (AutoCompleteTextView) findViewById(R.id.act_client);
        ACTV.setAdapter(autocomplete);


        //radio groups

        radioGroup = findViewById(R.id.selection);
        Button buttonsave = findViewById(R.id.save);



        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }
    public void validation(){
        int isSelected = radioGroup.getCheckedRadioButtonId();
        if (isSelected==1) {
            Toast.makeText(Client_settings.this, "You have not selected anything", Toast.LENGTH_LONG).show();
        }


            radio1 = findViewById(R.id.radioadmin);
           radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

               }
           });
        }}



        /*radio1 = findViewById(R.id.radioadmin);

        radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseDatabase.getInstance().getReference(String.valueOf(act_client))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                FirebaseDatabase.getInstance().getReference("AdminDb").setValue(dataSnapshot.getValue());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }*/


      /*  Button btn = (Button)findViewById(R.id.cancel);

        btn.setOnClickListener(v -> startActivity(new Intent(Client_settings.this, Client.class)));}
    }*/
