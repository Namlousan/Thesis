package com.thesis.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.net.HttpCookie;
import java.util.ArrayList;

public class Client_settings extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    String TAG = null;

    private RadioButton radio1, radio2;

    DatabaseReference ref, fromPath, toPath;

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

                    String firstname = suggestionSnapshot.child("firtname").getValue(String.class);
                    autocomplete.add(firstname);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        AutoCompleteTextView ACTV = findViewById(R.id.act_client);
        ACTV.setAdapter(autocomplete);

        copyRecord();
    }

    public void copyRecord(){

        Button buttonsave = findViewById(R.id.save);
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fromPath = FirebaseDatabase.getInstance().getReference().child("ClientDb");
                toPath = FirebaseDatabase.getInstance().getReference().child("AdminDB");

                fromPath.addValueEventListener(new ValueEventListener() {

                    private void moveRecord(DatabaseReference fromPath, final DatabaseReference toPath) {
                        ValueEventListener valueEventListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                toPath.setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isComplete()) {
                                            Log.d(TAG, "Success!");
                                        } else {
                                            Log.d(TAG, "Copy failed!");
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.d(TAG, error.getMessage()); //Don't ignore potential errors!
                            }
                        };
                        fromPath.addListenerForSingleValueEvent(valueEventListener);
                    }


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            };
         });
    }

    public void checkButton(View v){
        radioGroup = findViewById(R.id.selection);
        radio1 = findViewById(R.id.radioadmin);
        radio2 = findViewById(R.id.radio2);

        int radio = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radio);

        Toast.makeText(this, radioButton.getText() + "selected", Toast.LENGTH_SHORT).show();

        }
}



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
