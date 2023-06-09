package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.CompteList
import com.glsservice.tg.R

class CompteListeAdapter(private var dataList : ArrayList<CompteList>, private val context: Context): RecyclerView.Adapter<CompteListeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.items3,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val CompteList = dataList[position]
        holder.tel.text= CompteList.Telcompte
        holder.role.text = CompteList.role
        holder.date.text = CompteList.created_on


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemLayoutView: View): RecyclerView.ViewHolder(itemLayoutView) {
        var role : TextView
        var tel : TextView
        var date : TextView

        init {
            role=itemLayoutView.findViewById(R.id.RoleHolder)
            tel=itemLayoutView.findViewById(R.id.NumHolder)
            date=itemLayoutView.findViewById(R.id.DateCHolder)
        }

    }
}