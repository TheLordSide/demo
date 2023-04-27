package com.example.demo.Adapter
import android.view.View
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Apiclient.ApiResponse.ClientList
import com.example.demo.Apiclient.ApiResponse.ClientResponse
import com.example.demo.R
import tg.intaonline.intaonline.Adaptater.NotifyAdapter
import tg.intaonline.intaonline.ApiClient.ApiResponse.NotifyList

class ClientListeAdapter(private var dataList : ArrayList<ClientList>, private val context: Context): RecyclerView.Adapter<ClientListeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.items2,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val clientList = dataList[position]
        holder.nom.text= clientList.nomClient
        holder.prenom.text = clientList.prenomclient
        holder.tel.text = clientList.telcompte
        holder.sex.text = clientList.sexClient
        holder.pays.text=clientList.paysclient
        holder.qtier.text=clientList.quartierclient
        holder.date.text=clientList.dateClient
        holder.fachat.text=clientList.fachatclient
        holder.ville.text=clientList.villeclient

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemLayoutView: View):RecyclerView.ViewHolder(itemLayoutView) {
        var nom : TextView
        var prenom : TextView
        var tel : TextView
        var sex : TextView
        var ville : TextView
        var pays : TextView
        var qtier : TextView
        var date : TextView
        var fachat : TextView

        init {
            nom=itemLayoutView.findViewById(R.id.NomHolder)
            prenom=itemLayoutView.findViewById(R.id.PrenomHolder)
            tel=itemLayoutView.findViewById(R.id.TelHolder)
            sex=itemLayoutView.findViewById(R.id.SexHolder)
            ville=itemLayoutView.findViewById(R.id.VilleHolder)
            pays=itemLayoutView.findViewById(R.id.PaysHolder)
            qtier=itemLayoutView.findViewById(R.id.QuartierHolder)
            date=itemLayoutView.findViewById(R.id.DateHolder)
            fachat=itemLayoutView.findViewById(R.id.FachatHolder)
        }

    }
}