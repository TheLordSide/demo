package com.glsservice.tg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.ChatAdapter
import com.glsservice.tg.Apiclient.ApiRequest.AnswerRequest
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListeResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables

class AdminConvActivity : AppCompatActivity() {

    private val dataList = ArrayList<QuestionListe>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_conv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.recycler_gchat)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ChatAdapter(dataList, this)
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
}
