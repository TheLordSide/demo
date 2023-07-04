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
import com.glsservice.tg.Adapter.QuestionListeForAdminAdapter
import com.glsservice.tg.Apiclient.ApiRequest.AskQuestion
import com.glsservice.tg.Apiclient.ApiRequest.SendMessage
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.ApiResponse.AskQuestionResponse
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListeResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables

class ConversationActivity : AppCompatActivity() {
    private val dataList = ArrayList<QuestionListe>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
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

        val sendButton = findViewById<Button>(R.id.button_send)
        sendButton.setOnClickListener {

            sendQuestion()

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    private fun sendQuestion(){
        var getRole = GlobalVariables.roleGlobal
        val getTel = GlobalVariables.telGlobal
        val getTicket = GlobalVariables.ticketGlobal
        //var error = findViewById<TextInputLayout>(R.id.AskMessage)
        val getContent = findViewById<EditText>(R.id.gchat_message)

        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = SendMessage()
        request.QuestionClient = getContent.text.toString().trim()
        request.telClient = getTel.toString().trim()
        request.ticket = getTicket.toString().trim()

        api.sendmessage(request.QuestionClient,request.ticket,request.telClient)?.enqueue(object : Callback<AnswerResponse>{
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
