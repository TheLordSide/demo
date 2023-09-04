package com.glsservice.tg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.AgentListAdapter
import com.glsservice.tg.Adapter.JournauxTransferedAdapter
import com.glsservice.tg.Apiclient.ApiResponse.AgentListe
import com.glsservice.tg.Apiclient.ApiResponse.JournauxTransfered
import com.glsservice.tg.Apiclient.ApiResponse.JournauxTransferedResponse
import com.glsservice.tg.Apiclient.ApiResponse.NotifyResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class JournauxTransferedActivity : AppCompatActivity() {
    private val dataList = ArrayList<JournauxTransfered>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: JournauxTransferedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journaux_transfered)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = JournauxTransferedAdapter(dataList, this)
        recyclerView.adapter = adapter
        swipeRefreshLayout = findViewById(R.id.swipeRefresh2)
        if (dataList.isEmpty()) {
            getTransferedList()
        }
        swipeRefreshLayout.setOnRefreshListener {
            getTransferedList()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getTransferedList(){
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        api.getTransfered().enqueue(object : Callback<JournauxTransferedResponse> {
            override fun onResponse(
                call: Call<JournauxTransferedResponse>,
                response: Response<JournauxTransferedResponse>
            ) {
                val retour = response.body()?.liste
                val total = response.body()?.total
                val success = response.body()?.success
                if (swipeRefreshLayout.isRefreshing) {
                    swipeRefreshLayout.isRefreshing = false
                }
                if (response.isSuccessful) {
                    if (success == "true") {
                        dataList.clear() // clear the existing data
                        dataList.addAll(response.body()!!.liste)
                        recyclerView.adapter?.notifyDataSetChanged()

                    } else {
                        Toast.makeText(applicationContext, success, Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<JournauxTransferedResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }

        })


    }
}