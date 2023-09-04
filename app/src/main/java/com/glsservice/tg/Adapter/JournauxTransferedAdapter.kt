package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.JournauxTransfered
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.R

class JournauxTransferedAdapter (private var dataList: ArrayList<JournauxTransfered>, private val context: Context) :
    RecyclerView.Adapter<JournauxTransferedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items6, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notify = dataList[position]
        holder.dateTransfert.text = notify.Date
        holder.Qte.text = notify.Qte
        holder.tel.text = notify.Telcommercial
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var Qte: TextView
        var dateTransfert: TextView
        var tel: TextView

        init {
            Qte = itemLayoutView.findViewById(R.id.Qterecu)
            dateTransfert = itemLayoutView.findViewById(R.id.DateRecept)
            tel = itemLayoutView.findViewById(R.id.TelAgent)

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}