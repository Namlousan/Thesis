package com.thesis.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;
import java.util.ArrayList;

public class adminadapter extends RecyclerView.Adapter<adminadapter.adminviewholder> {
    ArrayList<Adminis> list;
    public adminadapter(ArrayList<Adminis>list){
        this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public adminviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adminrecyclerview,viewGroup,false);

        return new adminviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adminviewholder holder, int position) {
       holder.id.setText(list.get(position).getAdminID());
       holder.adminname.setText(list.get(position).getAdminName());
       holder.emailad.setText(list.get(position).getEmailAddress());
        holder.code.setText(list.get(position).getAdminDeptCode());

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
