package com.example.demo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import tg.intaonline.intaonline.ApiClient.ApiRequest.RegisterRequest
import tg.intaonline.intaonline.ApiClient.ApiResponse.RegisterResponse
import tg.intaonline.intaonline.ApiClient.service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val registerBtn = findViewById<Button>(R.id.Inscription)
        registerBtn.setOnClickListener {
            register()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun register() {
        val userTel = findViewById<EditText>(R.id.TelInput)
        val password = findViewById<EditText>(R.id.userPass)
        val passwordConfirmed = findViewById<EditText>(R.id.userPassconfirmed)
        var errorPassword = findViewById<TextInputLayout>(R.id.PassWord)
        var errorUserTel = findViewById<TextInputLayout>(R.id.Tel)
        val errorUserPassword = findViewById<TextInputLayout>(R.id.PassWord)


        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = RegisterRequest()
        request.phone = userTel.text.toString().trim()
        request.password = password.text.toString().trim()
        api.register(request.phone, request.password)?.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val message = response.body()?.message
                val success = response.body()?.success

                if (response.isSuccessful) {

                    if (success == "true") {
                        // A NE PAS TOUCHER
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })


    }


}