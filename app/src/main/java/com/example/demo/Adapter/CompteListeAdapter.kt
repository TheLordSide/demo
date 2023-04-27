package com.example.demo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Apiclient.ApiResponse.CompteList
import com.example.demo.R

class CompteListeAdapter(private var dataList : ArrayList<CompteList>, private val context: Context): RecyclerView.Adapter<CompteListeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.items3,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val CompteList = dataList[position]
        holder.tel.text= CompteList.Telcompte
        holder.role.text = CompteList.role
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemLayoutView: View): RecyclerView.ViewHolder(itemLayoutView) {
        var role : TextView
        var tel : TextView

        init {
            role=itemLayoutView.findViewById(R.id.RoleHolder)
            tel=itemLayoutView.findViewById(R.id.NumHolder)
        }

    }
}