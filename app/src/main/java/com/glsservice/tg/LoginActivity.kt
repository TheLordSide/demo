package com.glsservice.tg

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import tg.intaonline.intaonline.Model.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.ApiRequest.LoginRequest
import tg.intaonline.intaonline.ApiClient.ApiResponse.LoginResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import com.glsservice.tg.Notifications.showNotification
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val registerBtn = findViewById<Button>(R.id.Inscription)
        val connectBtn = findViewById<Button>(R.id.Connexion)
        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        connectBtn.setOnClickListener {
            login()
        }

    }

    override fun onBackPressed() {
        // laisser cette méthode vide pour bloquer le retour physique du bouton
    }

    private fun login() {
        val tel = findViewById<EditText>(R.id.UserTelInput)
        val password = findViewById<EditText>(R.id.userPass)
        val request = LoginRequest()
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        request.phone = tel.text.toString().trim()
        request.password = password.text.toString().trim()
        api.login(request.phone, request.password)?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val message = response.body()?.message
                val success = response.body()?.success
                val role = response.body()?.role
                val tel = response.body()?.tel
               // val telToPass =tel.toString()
               // val roleToPass = role.toString()
                if (response.isSuccessful) {
                    if (success == "true") {
                        if (role == "user") {
                            GlobalVariables.telGlobal = tel
                            GlobalVariables.roleGlobal = role
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                            showNotification(applicationContext, "Nouvelle notification", message.toString())
                        } else if (role == "admin") {
                            GlobalVariables.telAdmin = tel
                            GlobalVariables.roleGlobal = role
                            val intent = Intent(applicationContext, AdminActivity::class.java)
                            startActivity(intent)
                            finish()
                            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                            showNotification(applicationContext, "Nouvelle notification", message.toString())
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

            }

        })
    }



}