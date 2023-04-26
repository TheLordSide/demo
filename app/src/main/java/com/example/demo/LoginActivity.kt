package com.example.demo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import tg.intaonline.intaonline.Model.GlobalVariables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.ApiRequest.LoginRequest
import tg.intaonline.intaonline.ApiClient.ApiResponse.LoginResponse
import tg.intaonline.intaonline.ApiClient.service.ApiClient
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
                val telToPass =tel.toString()
                val roleToPass = role.toString()
                if (response.isSuccessful) {
                    if (success == "true") {
                        if (role == "user") {
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.putExtra("role", roleToPass)
                            intent.putExtra("tel", telToPass)
                            startActivity(intent)
                            finish()
                            Toast.makeText(applicationContext, message , Toast.LENGTH_SHORT).show()
                        } else if (role == "admin") {
                            val intent = Intent(applicationContext, AdminActivity::class.java)
                            intent.putExtra("role", roleToPass)
                            intent.putExtra("tel", telToPass)
                            startActivity(intent)
                            finish()
                            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
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