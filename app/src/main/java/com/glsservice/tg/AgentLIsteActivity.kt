package com.glsservice.tg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.AgentListAdapter
import com.glsservice.tg.Adapter.CompteListeAdapter
import com.glsservice.tg.Apiclient.ApiRequest.AgentDeleteRequest
import com.glsservice.tg.Apiclient.ApiRequest.ClientUpdateRequest
import com.glsservice.tg.Apiclient.ApiResponse.AgentListe
import com.glsservice.tg.Apiclient.ApiResponse.AgentListeResponse
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.ApiResponse.CompteList
import com.glsservice.tg.Apiclient.ApiResponse.CompteUpdateResponse
import com.glsservice.tg.Apiclient.ApiResponse.NotifyResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class AgentLIsteActivity : AppCompatActivity() {

    private val dataList = ArrayList<AgentListe>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: AgentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_liste)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = AgentListAdapter(dataList, this)
        recyclerView.adapter = adapter

        swipeRefreshLayout = findViewById(R.id.swipeRefresh2)
        if (dataList.isEmpty()) {
            getAgentListe()
        }
        swipeRefreshLayout.setOnRefreshListener {
            getAgentListe()
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getAgentListe() {
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)

        api.getAgents().enqueue(object : Callback<AgentListeResponse> {
            override fun onResponse(
                call: Call<AgentListeResponse>,
                response: Response<AgentListeResponse>
            ) {
                val retour = response.body()?.liste
                val total = response.body()?.total
                val success = response.body()?.success
                if (swipeRefreshLayout.isRefreshing) {
                    swipeRefreshLayout.isRefreshing = false
                }
                if (response.isSuccessful) {
                    if (success == "true") {
                        deleteAgent()
                        dataList.clear() // clear the existing data
                        dataList.addAll(response.body()!!.liste)
                        recyclerView.adapter?.notifyDataSetChanged()


                    } else {
                        Toast.makeText(applicationContext, success, Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<AgentListeResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false

            }

        })

    }


    private fun deleteAgent() {

        val gestureDetector =
            GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
                override fun onLongPress(e: MotionEvent) {
                    val view = recyclerView.findChildViewUnder(e.x, e.y)
                    if (view != null) {
                        val position = recyclerView.getChildAdapterPosition(view)
                        val selectedItem = dataList[position]
                        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
                        //val request = AgentDeleteRequest()
                        val valeur = selectedItem.nom.toString().trim()
                        val valeur2 = selectedItem.tel.toString().trim()
                        api.deleteAgent(valeur, valeur2)
                            ?.enqueue(object : Callback<AnswerResponse> {
                                override fun onResponse(
                                    call: Call<AnswerResponse>,
                                    response: Response<AnswerResponse>
                                ) {
                                    val message = response.body()?.message
                                    val success = response.body()?.success

                                    if (response.isSuccessful) {

                                        if (success == "true") {
                                            // A NE PAS TOUCHER
                                            recyclerView.adapter?.notifyDataSetChanged()
                                            getAgentListe()
                                        }
                                    }

                                }

                                override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                                    val message = t.localizedMessage
                                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            })

                    }
                }
            })
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                gestureDetector.onTouchEvent(e)
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

    }

}



