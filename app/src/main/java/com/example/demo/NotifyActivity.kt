package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Apiclient.ApiResponse.NotifyResponse
import com.example.demo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.Adaptater.NotifyAdapter
import tg.intaonline.intaonline.ApiClient.ApiResponse.NotifyList
import tg.intaonline.intaonline.ApiClient.service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class NotifyActivity : AppCompatActivity() {
    private val dataList = ArrayList<NotifyList>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotifyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = NotifyAdapter(dataList, this)
        recyclerView.adapter = adapter
        getNotificationList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getNotificationList() {
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)

        api.getNotify()?.enqueue(object : Callback<NotifyResponse> {
            override fun onResponse(
                call: Call<NotifyResponse>,
                response: Response<NotifyResponse>
            ) {
                val retour = response.body()?.liste
                val total = response.body()?.total
                val success = response.body()?.success
                if (response.isSuccessful) {
                    if (success == "true") {
                        dataList.addAll(response.body()!!.liste)
                        recyclerView.adapter?.notifyDataSetChanged()

                    } else {

                    }

                }
            }

            override fun onFailure(call: Call<NotifyResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

            }

        })

    }
}