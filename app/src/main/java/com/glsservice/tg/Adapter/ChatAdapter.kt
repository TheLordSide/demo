package com.glsservice.tg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.R

/*class ChatAdapter(private var dataList: ArrayList<QuestionListe>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val MESSAGE_QUESTIONS = 1
    private val MESSAGE_REPONSES = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MESSAGE_QUESTIONS -> {
                val view = LayoutInflater.from(context).inflate(R.layout.questions, parent, false)
                ViewHolderQuestions(view)
            }
            MESSAGE_REPONSES -> {
                val view = LayoutInflater.from(context).inflate(R.layout.reponses, parent, false)
                ViewHolderReponses(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]

        when (holder.itemViewType) {
            MESSAGE_QUESTIONS -> {
                val viewHolderItems = holder as ViewHolderQuestions
                viewHolderItems.text_gchat_date_me.text = item.Datequestion
                viewHolderItems.text_gchat_message_me.text = item.QuestionClient
                // Affichez d'autres informations de la question si nécessaire
            }
            MESSAGE_REPONSES -> {
                val viewHolderItems2 = holder as ViewHolderReponses
                viewHolderItems2.text_gchat_message_other.text = item.ReponseAdmin
                viewHolderItems2.text_gchat_date_other.text = item.Datereponse
                // Affichez d'autres informations de la réponse si nécessaire
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataList[position]
        return if (item.QuestionClient?.isNotEmpty()!!) {
            MESSAGE_QUESTIONS
        } else {
            MESSAGE_REPONSES
        }
    }

    class ViewHolderQuestions(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_gchat_date_me: TextView = itemView.findViewById(R.id.text_gchat_date_me)
        var text_gchat_message_me: TextView = itemView.findViewById(R.id.text_gchat_message_me)
        // Ajoutez des références à d'autres vues spécifiques à la question si nécessaire
    }

    class ViewHolderReponses(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_gchat_message_other: TextView = itemView.findViewById(R.id.text_gchat_message_other)
        var text_gchat_date_other: TextView = itemView.findViewById(R.id.text_gchat_date_other)
        // Ajoutez des références à d'autres vues spécifiques à la réponse si nécessaire
    }
}
*/

class ChatAdapter(private var dataList: ArrayList<QuestionListe>, private val context: Context) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
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
            question = itemLayoutView.findViewById(R.id.text_gchat_message_me)
            datequestion = itemLayoutView.findViewById(R.id.text_gchat_date_me)
            reponse = itemLayoutView.findViewById(R.id.text_gchat_message_other)
            daterponse = itemLayoutView.findViewById(R.id.text_gchat_date_other)

        }
    }


}