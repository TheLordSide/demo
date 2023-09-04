package com.glsservice.tg

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.glsservice.tg.Adapter.CompteListeAdapter
import com.glsservice.tg.Apiclient.ApiRequest.ClientUpdateRequest
import com.glsservice.tg.Apiclient.ApiResponse.CompteList
import com.glsservice.tg.Apiclient.ApiResponse.CompteListeResponse
import com.glsservice.tg.Apiclient.ApiResponse.CompteUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.glsservice.tg.Apiclient.Service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class CompteListeActivity : AppCompatActivity() {
    private val dataList = ArrayList<CompteList>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
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

        swipeRefreshLayout = findViewById(R.id.swipeRefresh2)
        if (dataList.isEmpty()) {
            getCompteList()
        }
        swipeRefreshLayout.setOnRefreshListener {
            getCompteList()
        }

        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                val view = recyclerView.findChildViewUnder(e.x, e.y)
                if (view != null) {
                    val position = recyclerView.getChildAdapterPosition(view)
                    val selectedItem = dataList[position]
                    edit(selectedItem)

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


   private fun edit(selectedItem : CompteList){


        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = ClientUpdateRequest()
        request.phone = selectedItem.Telcompte.toString().trim()
        request.role = selectedItem.role.toString().trim()


        val builder = AlertDialog.Builder(this@CompteListeActivity)

        // Définir le titre et le message de la boîte de dialogue
        builder.setTitle("Alerte")
        builder.setMessage("Voulez-vous confirmer la mise à jour?")

        // Ajouter un bouton pour fermer la boîte de dialogue
        builder.setNegativeButton("annuler") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss() // Ferme la boîte de dialogue
        }
        builder.setPositiveButton("Confirmer") { dialogInterface: DialogInterface, _: Int ->

            api.editcompte(request.phone,request.role)?.enqueue(object : Callback<CompteUpdateResponse> {
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
                            getCompteList()
                            Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                                .show()

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

        val dialog = builder.create()
        dialog.show()

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

                }
            }

            override fun onFailure(call: Call<CompteListeResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }


        })
    }

}