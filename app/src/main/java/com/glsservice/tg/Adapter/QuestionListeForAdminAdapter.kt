package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.R

class QuestionListeForAdminAdapter (private var dataList : ArrayList<QuestionListe>, private val context: Context): RecyclerView.Adapter<QuestionListeForAdminAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items4, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notify = dataList[position]
        holder.questionContent.text = notify.QuestionClient
        holder.dateQuestion.text = notify.Datequestion
        holder.telClientQuestion.text = notify.TelClient
        holder.ticketQuestion.text = notify.Ticket
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var questionContent: TextView
        var dateQuestion: TextView
        var telClientQuestion: TextView
        var ticketQuestion: TextView

        init {
            questionContent = itemLayoutView.findViewById(R.id.QuestionHolder)
            dateQuestion = itemLayoutView.findViewById(R.id.DateQuestionHolder)
            telClientQuestion = itemLayoutView.findViewById(R.id.TelClientHolder)
            ticketQuestion = itemLayoutView.findViewById(R.id.TicketHolder)
        }

    }

}