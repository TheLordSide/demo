package com.glsservice.tg

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.ChatAdapterAdmin
import com.glsservice.tg.Apiclient.ApiRequest.AnswerRequest
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListeResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables

class AdminConvActivity : AppCompatActivity() {

    private val dataList = ArrayList<QuestionListe>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ChatAdapterAdmin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_conv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.recycler_gchat)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ChatAdapterAdmin(dataList, this)
        recyclerView.adapter = adapter
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        if (dataList.isEmpty()) {
            getChatList()
        }
        swipeRefreshLayout.setOnRefreshListener {
            getChatList()
        }

        val sendButton = findViewById<Button>(R.id.button_gchat_send)
        sendButton.setOnClickListener {
            answerQuestion()

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    private fun answerQuestion() {
        var getTicket = GlobalVariables.ticketGlobal
        val getTel = GlobalVariables.telGlobal
        //var error = findViewById<TextInputLayout>(R.id.NewAnswer)
        val getContent = findViewById<EditText>(R.id.edit_gchat_mesage)


        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = AnswerRequest()
        request.ticket = getTicket.toString().trim()
        request.telClient = getTel.toString().trim()
        request.reponse = getContent.text.toString().trim()

        api.receivemessage(request.reponse,request.ticket,request.telClient)?.enqueue(object : Callback<AnswerResponse>{
            override fun onResponse(
                call: Call<AnswerResponse>,
                response: Response<AnswerResponse>
            ) {
                val message = response.body()?.message
                val success = response.body()?.success

                if (response.isSuccessful) {

                    if (success == "true") {
                        // A NE PAS TOUCHER
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                        getContent.text.clear()
                        getChatList()
                        showNotification(applicationContext, "Nouvelle notification", "Ceci est le contenu de la notification.")

                    } else {
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }

        })
    }



    private fun getChatList() {
        val valeur = GlobalVariables.telGlobal.toString().trim()
        val valeur2 = GlobalVariables.ticketGlobal.toString().trim()

        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)

        api.getChatHistory(valeur,valeur2)?.enqueue(object : Callback<QuestionListeResponse> {
            override fun onResponse(
                call: Call<QuestionListeResponse>,
                response: Response<QuestionListeResponse>
            ) {
                val success = response.body()?.success
                if(swipeRefreshLayout.isRefreshing){
                    swipeRefreshLayout.isRefreshing = false
                }

                if (response.isSuccessful) {
                    dataList.clear() // Effacer les données existantes
                    dataList.addAll(response.body()?.liste ?: emptyList()) // Ajouter les nouvelles données
                    adapter.notifyDataSetChanged() // Notifier l'adaptateur du changement de données
                }
            }

            override fun onFailure(call: Call<QuestionListeResponse>, t: Throwable) {
                val message = t.localizedMessage
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun showNotification(context: Context, titre: String, content: String){
        val idNotification = 1
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "mon_canal"
            val channelNom = "Mon Canal de Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelNom, importance)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, "mon_canal")
            .setSmallIcon(R.drawable.logo) // Icône de la notification
            .setContentTitle(titre) // Titre de la notification
            .setContentText(content) // Contenu de la notification
            .setAutoCancel(true) // Fermer la notification automatiquement lorsqu'elle est cliquée

        // Afficher la notification
        notificationManager.notify(idNotification, notificationBuilder.build())
    }
}
