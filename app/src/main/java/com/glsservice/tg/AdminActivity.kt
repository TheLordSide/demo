package com.glsservice.tg

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val relative1 = findViewById<RelativeLayout>(R.id.relativeLayout1)
        relative1.setOnClickListener {
            val intent = Intent(this, AdminNotifyActivity::class.java)
            startActivity(intent)
        }
        val relative2 = findViewById<RelativeLayout>(R.id.relativeLayout2)
        relative2.setOnClickListener {
            val intent = Intent(this, AdminDiscussionActivity::class.java)
            startActivity(intent)
        }
        val relative3 = findViewById<RelativeLayout>(R.id.relativeLayout3)
        relative3.setOnClickListener {
            val intent = Intent(this, CompteListeActivity::class.java)
            startActivity(intent)
        }
        val relative4 = findViewById<RelativeLayout>(R.id.relativeLayout4)
        relative4.setOnClickListener {
            val intent = Intent(this, NotifyActivity::class.java)
            startActivity(intent)
        }

        val relative5 = findViewById<RelativeLayout>(R.id.relativeLayout5)
        relative5.setOnClickListener {
            deleteAllNotifications()
        }

        val relative6 = findViewById<RelativeLayout>(R.id.relativeLayout6)
        relative6.setOnClickListener {
            deleteAllConversation()
        }
        val relative7 = findViewById<RelativeLayout>(R.id.relativeLayout7)
        relative7.setOnClickListener {
            val intent = Intent(this, AgentActivity::class.java)
            startActivity(intent)        }

    }


    private fun deleteAllNotifications() {
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        api.deleteAllNotifications().enqueue(object : Callback<AnswerResponse> {
            override fun onResponse(
                call: Call<AnswerResponse>,
                response: Response<AnswerResponse>
            ) {
                val message = response.body()?.message

                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })
    }



    private fun deleteAllConversation() {
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        api.deleteAllConversation().enqueue(object : Callback<AnswerResponse> {
            override fun onResponse(
                call: Call<AnswerResponse>,
                response: Response<AnswerResponse>
            ) {
                val message = response.body()?.message

                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })
    }


}