package com.thesis.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.thesis.admin.data.Clients;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class clientsdtradapter extends FirebaseRecyclerAdapter<clientsdtr, clientsdtradapter.adminviewholder> {


    public clientsdtradapter(@NotNull FirebaseRecyclerOptions<clientsdtr> options) {
        super(options);
    }

    @NonNull
    @NotNull
    @Override
    public adminviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item,parent,false);

        return new adminviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull adminviewholder holder,final int position, @NonNull clientsdtr model) {
        holder.id.setText(model.getName());
        holder.emailad.setText(model.getEmpid());
        holder.code.setText(model.getLogs());
        holder.date.setText(model.getDate());



    }


    class adminviewholder extends RecyclerView.ViewHolder{
        TextView id, emailad, code, date;


        public adminviewholder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.id);
            code= itemView.findViewById(R.id.code);
            date= itemView.findViewById(R.id.date);
            emailad= itemView.findViewById(R.id.adminname);


        }
    }

}
