package com.glsservice.tg

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.NotificationsForAdminAdapter
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.ApiResponse.NotifyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.ApiResponse.NotifyList
import com.glsservice.tg.Apiclient.Service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class NotifyActivity : AppCompatActivity() {
    private val dataList = ArrayList<NotifyList>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationsForAdminAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.Notification)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = NotificationsForAdminAdapter(dataList, this)
        recyclerView.adapter = adapter

        swipeRefreshLayout = findViewById(R.id.swipeRefresh2)
        if (dataList.isEmpty()) {
            getNotificationList()
        }
        swipeRefreshLayout.setOnRefreshListener {
            getNotificationList()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getNotificationList() {
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)

        api.getNotify().enqueue(object : Callback<NotifyResponse> {
            override fun onResponse(
                call: Call<NotifyResponse>,
                response: Response<NotifyResponse>
            ) {
                val retour = response.body()?.liste
                val total = response.body()?.total
                val success = response.body()?.success
                if (swipeRefreshLayout.isRefreshing) {
                    swipeRefreshLayout.isRefreshing = false
                }
                if (response.isSuccessful) {
                    if (success == "true") {
                        deleteNotification()
                        dataList.clear() // clear the existing data
                        dataList.addAll(response.body()!!.liste)
                        recyclerView.adapter?.notifyDataSetChanged()


                    } else {
                        Toast.makeText(applicationContext, success, Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<NotifyResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false

            }

        })

    }

    private fun deleteNotification() {
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent)   {
                val view = recyclerView.findChildViewUnder(e.x, e.y)
                if (view != null) {
                    val position = recyclerView.getChildAdapterPosition(view)
                    val selectedItem = dataList[position]
                    val valeur = selectedItem.IdNotification.toString().trim()
                    val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
                    val builder = AlertDialog.Builder(this@NotifyActivity)

                    // Définir le titre et le message de la boîte de dialogue
                    builder.setTitle("Alerte")
                    builder.setMessage("Voulez-vous confirmer la suppression?")

                    // Ajouter un bouton pour fermer la boîte de dialogue
                    builder.setNegativeButton("annuler") { dialogInterface: DialogInterface, _: Int ->
                        dialogInterface.dismiss() // Ferme la boîte de dialogue
                    }
                    builder.setPositiveButton("Confirmer") { dialogInterface: DialogInterface, _: Int ->
                        api.deleteNotification(valeur)?.enqueue(object : Callback<AnswerResponse> {
                            override fun onResponse(
                                call: Call<AnswerResponse>,
                                response: Response<AnswerResponse>
                            ) {
                                val message = response.body()?.message
                                if (response.isSuccessful) {
                                    getNotificationList()
                                }
                            }

                            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                                val message = t.localizedMessage
                                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                            }

                        })// Ferme la boîte de dialogue
                    }

                    // Créez et affichez la boîte de dialogue
                    val dialog = builder.create()
                    dialog.show()



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
