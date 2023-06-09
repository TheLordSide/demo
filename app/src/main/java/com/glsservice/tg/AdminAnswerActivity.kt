package com.glsservice.tg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.glsservice.tg.Apiclient.ApiRequest.AnswerRequest
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.textfield.TextInputLayout
import com.glsservice.tg.Apiclient.Service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables

class AdminAnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_answer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val answerBtn = findViewById<Button>(R.id.SendAnswer)
        answerBtn.setOnClickListener {
            answerQuestion()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun answerQuestion() {
        var getTicket = GlobalVariables.ticketGlobal
        val getTel = GlobalVariables.telClientAskGlobal
        var error = findViewById<TextInputLayout>(R.id.NewAnswer)
        val getContent = findViewById<EditText>(R.id.CreateAnswer)
        if (getContent.text.toString().trim().isEmpty()){

            error.error = getString(R.string.contenuQuestionVide)
            return
        }
        else{
            error.isErrorEnabled =false
        }

        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = AnswerRequest()
        request.ticket = getTicket.toString().trim()
        request.telClient = getTel.toString().trim()
        request.reponse = getContent.text.toString().trim()

        api.answerAdmin(request.reponse,request.ticket,request.telClient)?.enqueue(object : Callback<AnswerResponse>{
            override fun onResponse(
                call: Call<AnswerResponse>,
                response: Response<AnswerResponse>
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

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}