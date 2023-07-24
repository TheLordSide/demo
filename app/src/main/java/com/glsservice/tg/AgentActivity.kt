package com.glsservice.tg

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.glsservice.tg.Apiclient.ApiRequest.CreateAgentRequest
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import com.glsservice.tg.R
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.ApiRequest.LoginRequest
import tg.intaonline.intaonline.ApiClient.ApiResponse.LoginResponse
import tg.intaonline.intaonline.ApiClient.service.ApiInterface

class AgentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val agentBtn = findViewById<Button>(R.id.createAgent)
        agentBtn.setOnClickListener {
            createAgent()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Liste -> {
                val intent = Intent(applicationContext, AgentLIsteActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_transfert ->{
                val  intent = Intent(applicationContext, TrtansfertJournalActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun createAgent() {
        val nom = findViewById<EditText>(R.id.NomPrenomInput)
        val ville = findViewById<EditText>(R.id.TownInput)
        val quartier = findViewById<EditText>(R.id.QuartierInput)
        val phone = findViewById<EditText>(R.id.PhoneInput)

        val errorNom = findViewById<TextInputLayout>(R.id.NomPrenom)
        val errorVille = findViewById<TextInputLayout>(R.id.Town)
        val errorQuartier = findViewById<TextInputLayout>(R.id.Quartier)
        val errorPhone = findViewById<TextInputLayout>(R.id.Phone)


        if (nom.text.toString().trim().isEmpty()) {
            errorNom.error = "Le nom est vide"
            return
        } else {
            errorNom.isErrorEnabled = false
        }

        if (ville.text.toString().trim().isEmpty()) {
            errorVille.error =  "La ville est vide"
            return
        } else {
            errorVille.isErrorEnabled = false
        }

        if (quartier.text.toString().trim().isEmpty()) {
            errorQuartier.error = "Le quartier est vide"
            return
        } else {
            errorQuartier.isErrorEnabled = false
        }

        if (phone.text.toString().trim().isEmpty()) {
            errorPhone.error = getString(R.string.telVide)
            return
        } else {
            errorPhone.isErrorEnabled = false
        }


        val request = CreateAgentRequest()
        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        request.nom = nom.text.toString().trim()
        request.ville = ville.text.toString().trim()
        request.quartier = quartier.text.toString().trim()
        request.tel = phone.text.toString().trim()

        api.createAgent(request.nom, request.ville, request.quartier, request.tel)
            .enqueue(object : Callback<AnswerResponse> {
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