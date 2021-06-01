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

class adminadapter extends FirebaseRecyclerAdapter<Adminis, adminadapter.adminviewholder> {


    public adminadapter(@NotNull FirebaseRecyclerOptions<Adminis> options) {
        super(options);
    }

    @NonNull
    @NotNull
    @Override
    public adminviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminrecyclerview,parent,false);

        return new adminviewholder(view);    
    }

    @Override
    protected void onBindViewHolder(@NonNull adminviewholder holder,final int position, @NonNull Adminis model) {
        holder.id.setText(model.getAdminID());
        holder.adminname.setText(model.getAdminName());
        holder.emailad.setText(model.getEmailAddress());
        holder.code.setText(model.getAs());


        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.adminname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.popupadmin_update))
                        .setExpanded(true, 1400)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.editname);
                EditText idd = view.findViewById(R.id.editid);
                EditText emaill = view.findViewById(R.id.editemail);
                EditText pll = view.findViewById(R.id.editpl);

                Button updatebutton = view.findViewById(R.id.updatebtn);

                name.setText(model.getAdminName());
                idd.setText(model.getAdminID());
                emaill.setText(model.getEmailAddress());
                pll.setText(model.getAs());

                dialogPlus.show();

                updatebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("AdminName", name.getText().toString());
                        map.put("AdminID", idd.getText().toString());
                        map.put("EmailAddress", emaill.getText().toString());
                        map.put("As", pll.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("AdminDB")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.adminname.getContext(), "Updated succes" +
                                                "sfully!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.adminname.getContext(), "Error while updating!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(holder.adminname.getContext());
                alertDialog.setTitle("Are you sure?");
                alertDialog.setMessage("Deleted data cannot be undo");
                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("AdminDB")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.adminname.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.adminname.getContext(), "Canceled!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();


            }
        });

    }


    class adminviewholder extends RecyclerView.ViewHolder{
        TextView id, adminname, emailad, code;

        Button editbtn, deletebtn;


        public adminviewholder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.id);
            adminname= itemView.findViewById(R.id.adminname);
            emailad= itemView.findViewById(R.id.emailad);
            code= itemView.findViewById(R.id.code);

            editbtn= itemView.findViewById(R.id.editbtn);
            deletebtn= itemView.findViewById(R.id.deletebtn);

        }
    }

}
