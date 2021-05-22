package com.thesis.admin
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class CHAdapter(private val clientList:ArrayList<User>) : RecyclerView.Adapter<CHAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.client_item,parent,false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = clientList[position]
        holder.id.text = currentitem.empid
        holder.clientname.text = currentitem.firtname
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val id : TextView = itemView.findViewById(R.id.ID)
        val clientname : TextView = itemView.findViewById(R.id.Client_name)
    }
}
