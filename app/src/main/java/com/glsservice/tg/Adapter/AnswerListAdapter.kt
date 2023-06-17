package com.glsservice.tg.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.R

class AnswerListAdapter(private var dataList : ArrayList<QuestionListe>): RecyclerView.Adapter<AnswerListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items5, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notify = dataList[position]
        holder.question.text= notify.QuestionClient
        holder.date.text = notify.Datequestion       // holder.reponse.text = notify.ReponseAdmin
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var question: TextView
        var date: TextView
      //  var reponse: TextView

        init {
            question = itemLayoutView.findViewById(R.id.QuestionHolder)
            date = itemLayoutView.findViewById(R.id.DateReponseHolder)
           // reponse = itemLayoutView.findViewById(R.id.ReponseHolder)
        }
    }
}