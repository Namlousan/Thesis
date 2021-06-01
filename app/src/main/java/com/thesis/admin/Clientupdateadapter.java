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

class Clientupdateadapter extends FirebaseRecyclerAdapter<Clients, Clientupdateadapter.adminviewholder> {


    public Clientupdateadapter(@NotNull FirebaseRecyclerOptions<Clients> options) {
        super(options);
    }

    @NonNull
    @NotNull
    @Override
    public adminviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.updateclient,parent,false);

        return new adminviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull adminviewholder holder,final int position, @NonNull Clients model) {
        holder.id.setText(model.getEmpid());
        holder.adminname.setText(model.getFirtname());
        holder.emailad.setText(model.getEmail());
        holder.code.setText(model.getPlateNumber());

        holder.viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.adminname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.view_popup))
                        .setExpanded(true, 1400)
                        .create();
                View view = dialogPlus.getHolderView();

                TextView name = view.findViewById(R.id.nametxt);
                TextView idd = view.findViewById(R.id.idtxt);
                TextView emaill = view.findViewById(R.id.emailtxt);
                TextView pll = view.findViewById(R.id.pltxt);
                TextView cc = view.findViewById(R.id.codetxt);
                TextView branch = view.findViewById(R.id.branchtxt);
                TextView brand = view.findViewById(R.id.brandtxt);
                TextView col = view.findViewById(R.id.collegetxt);
                TextView dept = view.findViewById(R.id.depttxt);
                TextView colorr = view.findViewById(R.id.colortxt);

                name.setText(model.getFirtname());
                idd.setText(model.getEmpid());
                emaill.setText(model.getEmail());
                pll.setText(model.getPlateNumber());
                cc.setText(model.getClientcode());
                branch.setText(model.getBranch());
                brand.setText(model.getBrand());
                col.setText(model.getDepartment());
                dept.setText(model.getDeptcode());
                colorr.setText(model.getColorr());


                dialogPlus.show();

            }
        });

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.adminname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1400)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.editname);
                EditText idd = view.findViewById(R.id.editid);
                EditText emaill = view.findViewById(R.id.editemail);
                EditText pll = view.findViewById(R.id.editpl);
                EditText branch = view.findViewById(R.id.branchedit);
                EditText brand = view.findViewById(R.id.editbrand);
                EditText cc = view.findViewById(R.id.cedit);
                EditText dept = view.findViewById(R.id.deptedit);
                EditText col = view.findViewById(R.id.coledit);
                EditText colorr = view.findViewById(R.id.editcolor);

                Button updatebutton = view.findViewById(R.id.updatebtn);

                name.setText(model.getFirtname());
                idd.setText(model.getEmpid());
                emaill.setText(model.getEmail());
                pll.setText(model.getPlateNumber());
                cc.setText(model.getClientcode());
                branch.setText(model.getBranch());
                brand.setText(model.getBrand());
                col.setText(model.getDepartment());
                dept.setText(model.getDeptcode());
                colorr.setText(model.getColorr());

                dialogPlus.show();

                updatebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("firtname", name.getText().toString());
                        map.put("empid", idd.getText().toString());
                        map.put("email", emaill.getText().toString());
                        map.put("plateNumber", pll.getText().toString());
                        map.put("branch", branch.getText().toString());
                        map.put("brand", brand.getText().toString());
                        map.put("colorr", colorr.getText().toString());
                        map.put("department", col.getText().toString());
                        map.put("deptcode", dept.getText().toString());
                        map.put("clientcode", cc.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("ClientDb")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.adminname.getContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();
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
                        FirebaseDatabase.getInstance().getReference().child("ClientDb")
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

        Button editbtn, deletebtn, viewbtn;


        public adminviewholder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.empidtext);
            adminname= itemView.findViewById(R.id.nametext);
            emailad= itemView.findViewById(R.id.emailtext);
            code= itemView.findViewById(R.id.pltext);

            editbtn= itemView.findViewById(R.id.editbtn);
            deletebtn= itemView.findViewById(R.id.deletebtn);
            viewbtn= itemView.findViewById(R.id.viewbtn);

        }
    }

}
