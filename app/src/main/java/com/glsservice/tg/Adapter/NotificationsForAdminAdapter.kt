package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.R
import tg.intaonline.intaonline.ApiClient.ApiResponse.NotifyList

class NotificationsForAdminAdapter (private var dataList : ArrayList<NotifyList>,  private val context: Context): RecyclerView.Adapter<NotificationsForAdminAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notify = dataList[position]
        holder.contentOfNotication.text = notify.ContentNotification
        holder.datenotif.text = notify.DateNotification
        holder.auteur.text = notify.Auteur
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var contentOfNotication: TextView
        var datenotif: TextView
        var auteur: TextView

        init {
            contentOfNotication = itemLayoutView.findViewById(R.id.ContentHolder)
            datenotif = itemLayoutView.findViewById(R.id.DateHolder)
            auteur = itemLayoutView.findViewById(R.id.AuteurHolder)
        }

    }
}