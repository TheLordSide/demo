package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.AgentListe
import com.glsservice.tg.R

class AgentListAdapter (private var dataList : ArrayList<AgentListe>, private val context: Context): RecyclerView.Adapter<AgentListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items2, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val AgentList = dataList[position]
        holder.tel.text= AgentList.tel
        holder.ville.text = AgentList.ville
        holder.quartier.text = AgentList.quartier
        holder.nom.text = AgentList.nom
    }

    class ViewHolder(itemLayoutView: View): RecyclerView.ViewHolder(itemLayoutView) {
        var nom : TextView
        var quartier : TextView
        var ville : TextView
        var tel : TextView


        init {
            nom=itemLayoutView.findViewById(R.id.NomHolder)
            quartier=itemLayoutView.findViewById(R.id.QuartierHolder)
            tel=itemLayoutView.findViewById(R.id.TelHolder)
            ville=itemLayoutView.findViewById(R.id.VilleHolder)
        }

    }

}