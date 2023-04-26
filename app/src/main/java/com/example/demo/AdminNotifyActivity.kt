package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.demo.Apiclient.ApiRequest.NotifyRequest
import com.example.demo.Apiclient.ApiResponse.NotifyCreationResponse
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.ApiRequest.RegisterRequest
import tg.intaonline.intaonline.ApiClient.ApiResponse.RegisterResponse
import tg.intaonline.intaonline.ApiClient.service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class AdminNotifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_notify)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val notifyBtn = findViewById<Button>(R.id.createNotify)
        notifyBtn.setOnClickListener {
            createNotification()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun createNotification(){
        var getRole = intent.getStringExtra("role")
        val getTel = intent.getStringExtra("tel")
        val content = findViewById<EditText>(R.id.CreateNote)

        val passwordConfirmed = findViewById<EditText>(R.id.userPassconfirmed)
        var errorPassword = findViewById<TextInputLayout>(R.id.PassWord)
        var errorUserTel = findViewById<TextInputLayout>(R.id.Tel)
        val errorUserPassword = findViewById<TextInputLayout>(R.id.PassWord)


        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = NotifyRequest()
        request.contentNotify = content.text.toString().trim()
        request.auteur = "admin"
        api.createnotify(request.contentNotify, request.auteur)?.enqueue(object : Callback<NotifyCreationResponse> {
            override fun onResponse(
                call: Call<NotifyCreationResponse>,
                response: Response<NotifyCreationResponse>
            ) {
                val message = response.body()?.message
                val success = response.body()?.success

                if (response.isSuccessful) {

                    if (success == "true") {
                        // A NE PAS TOUCHER
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                        content.text.clear()
                    } else {
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }

            override fun onFailure(call: Call<NotifyCreationResponse>, t: Throwable) {
                val message= t.localizedMessage
                Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
            }
        })

    }
}