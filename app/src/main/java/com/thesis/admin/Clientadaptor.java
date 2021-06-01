package com.thesis.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.admin.data.Clients;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;
import java.util.ArrayList;

public class Clientadaptor extends RecyclerView.Adapter<Clientadaptor.adminviewholder> {
    ArrayList<Clients> list;
    public Clientadaptor(ArrayList<Clients>list){
        this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public adminviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_clientadaptor,viewGroup,false);

        return new adminviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adminviewholder holder, int position) {
        holder.id.setText(list.get(position).getEmpid());
        holder.adminname.setText(list.get(position).getFirtname());
        holder.emailad.setText(list.get(position).getEmail());
        holder.code.setText(list.get(position).getPlateNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class adminviewholder extends RecyclerView.ViewHolder{
        TextView id, adminname, emailad, code;


        public adminviewholder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.ID);
            adminname= itemView.findViewById(R.id.Adminname);
            emailad= itemView.findViewById(R.id.LogStat);
            code= itemView.findViewById(R.id.Date);

        }
    }
}
