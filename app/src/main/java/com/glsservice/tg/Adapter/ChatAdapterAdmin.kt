package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.R

class ChatAdapterAdmin(private var dataList: ArrayList<QuestionListe>, private val context: Context) :
    RecyclerView.Adapter<ChatAdapterAdmin.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item_admin, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notify = dataList[position]
        holder.question.text = notify.QuestionClient
        holder.datequestion.text = notify.Datequestion
        holder.daterponse.text = notify.Datereponse
        holder.reponse.text = notify.ReponseAdmin
    }
    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var question: TextView
        var datequestion: TextView
        var reponse: TextView
        var daterponse: TextView

        init {
            question = itemLayoutView.findViewById(R.id.text_gchat_message_o)
            datequestion = itemLayoutView.findViewById(R.id.text_gchat_date_o)
            reponse = itemLayoutView.findViewById(R.id.text_gchat_message)
            daterponse = itemLayoutView.findViewById(R.id.text_gchat_date)

        }
    }

}