package com.glsservice.tg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.glsservice.tg.Apiclient.ApiRequest.AskQuestion
import com.glsservice.tg.Apiclient.ApiResponse.AskQuestionResponse
import com.google.android.material.textfield.TextInputLayout
import com.glsservice.tg.Apiclient.Service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables

class ClientAskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_ask)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val submitBtn = findViewById<Button>(R.id.submitQuestion)
        submitBtn.setOnClickListener {
            sendQuestion()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun sendQuestion(){
        var getRole = GlobalVariables.roleGlobal
        val getTel = GlobalVariables.telGlobal
        var error = findViewById<TextInputLayout>(R.id.AskMessage)
        val getContent = findViewById<EditText>(R.id.CreateMessage)
        if (getContent.text.toString().trim().isEmpty()){

            error.error = getString(R.string.contenuQuestionVide)
            return
        }
        else{
            error.isErrorEnabled =false
        }

        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = AskQuestion()
        request.QuestionClient = getContent.text.toString().trim()
        request.telClient = getTel.toString().trim()

        api.askmessage(request.QuestionClient,request.telClient)?.enqueue(object : Callback<AskQuestionResponse>{
            override fun onResponse(
                call: Call<AskQuestionResponse>,
                response: Response<AskQuestionResponse>
            ) {
                val message = response.body()?.message
                val success = response.body()?.success

                if (response.isSuccessful) {

                    if (success == "true") {
                        // A NE PAS TOUCHER
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                        getContent.text.clear()
                    } else {
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<AskQuestionResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

            }

        })

    }





}