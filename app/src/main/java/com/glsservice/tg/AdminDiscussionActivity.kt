package com.glsservice.tg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.QuestionListeForAdminAdapter
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListe
import com.glsservice.tg.Apiclient.ApiResponse.QuestionListeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.glsservice.tg.Apiclient.Service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables

class AdminDiscussionActivity : AppCompatActivity() {
    private val dataList = ArrayList<QuestionListe>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: QuestionListeForAdminAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_discussion)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter =QuestionListeForAdminAdapter(dataList, this)
        recyclerView.adapter = adapter

        swipeRefreshLayout = findViewById(R.id.swipeRefresh2)
        if (dataList.isEmpty()) {
            getQuestionList()
        }
        swipeRefreshLayout.setOnRefreshListener {
            getQuestionList()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getQuestionList(){
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        api.getquestions()?.enqueue(object : Callback<QuestionListeResponse> {
            override fun onResponse(
                call: Call<QuestionListeResponse>,
                response: Response<QuestionListeResponse>
            ) {
                val retour = response.body()?.liste
                val total = response.body()?.total
                val success = response.body()?.success
                if(swipeRefreshLayout.isRefreshing){
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
                    getTicket()
                }
            }

            override fun onFailure(call: Call<QuestionListeResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
                //Log.d("MonApplication", "Valeur de getRole : $message")

            }

        })
    }

    private fun getTicket() {
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                val view = recyclerView.findChildViewUnder(e.x, e.y)
                if (view != null) {
                    val position = recyclerView.getChildAdapterPosition(view)
                    val selectedItem = dataList[position]
                    GlobalVariables.ticketGlobal = selectedItem.Ticket.toString().trim()
                    GlobalVariables.telGlobal = selectedItem.TelClient.toString().trim()
                    val intent = Intent(applicationContext, AdminConvActivity::class.java)
                    startActivity(intent)

                }
                return true
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