package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Adapter.CompteListeAdapter
import com.example.demo.Apiclient.ApiRequest.ClientUpdateRequest
import com.example.demo.Apiclient.ApiResponse.CompteList
import com.example.demo.Apiclient.ApiResponse.CompteListeResponse
import com.example.demo.Apiclient.ApiResponse.CompteUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.ApiResponse.RegisterResponse
import tg.intaonline.intaonline.ApiClient.service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class CompteListeActivity : AppCompatActivity() {
    private val dataList = ArrayList<CompteList>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CompteListeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compte_liste)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CompteListeAdapter(dataList, this)
        recyclerView.adapter = adapter

        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                val view = recyclerView.findChildViewUnder(e.x, e.y)
                if (view != null) {
                    val position = recyclerView.getChildAdapterPosition(view)
                    val selectedItem = dataList[position]
                    val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
                    val request = ClientUpdateRequest()
                    request.phone = selectedItem.Telcompte.toString().trim()
                    api.editcompte(request.phone)?.enqueue(object : Callback<CompteUpdateResponse> {
                        override fun onResponse(
                            call: Call<CompteUpdateResponse>,
                            response: Response<CompteUpdateResponse>
                        ) {
                            val message = response.body()?.message
                            val success = response.body()?.success

                            if (response.isSuccessful) {

                                if (success == "true") {
                                    // A NE PAS TOUCHER
                                    recyclerView.adapter?.notifyDataSetChanged()
                                } else {
                                    Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                                        .show()
                                }
                            }

                        }

                        override fun onFailure(call: Call<CompteUpdateResponse>, t: Throwable) {
                            val message = t.localizedMessage
                            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
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

        getCompteList()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private  fun  getCompteList(){
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        api.getAccounts()?.enqueue(object : Callback<CompteListeResponse> {
            override fun onResponse(
                call: Call<CompteListeResponse>,
                response: Response<CompteListeResponse>
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

            override fun onFailure(call: Call<CompteListeResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

            }


        })
    }

}