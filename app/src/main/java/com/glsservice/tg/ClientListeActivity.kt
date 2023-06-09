package com.glsservice.tg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glsservice.tg.Adapter.ClientListeAdapter
import com.glsservice.tg.Apiclient.ApiResponse.ClientList
import com.glsservice.tg.Apiclient.ApiResponse.ClientListeResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientListeActivity : AppCompatActivity() {
    private val dataList = ArrayList<ClientList>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ClientListeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_liste)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ClientListeAdapter(dataList, this)
        recyclerView.adapter = adapter
        getClientList()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private  fun  getClientList(){
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        api.getList()?.enqueue(object : Callback<ClientListeResponse> {
            override fun onResponse(
                call: Call<ClientListeResponse>,
                response: Response<ClientListeResponse>
            ) {
                val retour = response.body()?.liste
                val total = response.body()?.total
                val success = response.body()?.success
                if (response.isSuccessful) {
                    if (success == "true") {
                        dataList.addAll(response.body()!!.liste)
                        recyclerView.adapter?.notifyDataSetChanged()

                    } else {
                        Toast.makeText(applicationContext, success, Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<ClientListeResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

            }


        })
    }
}
