package com.glsservice.tg

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.glsservice.tg.Apiclient.ApiRequest.ClientRequest
import com.glsservice.tg.Apiclient.ApiResponse.ClientResponse
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.glsservice.tg.Apiclient.Service.ApiClient
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ClientActivity : AppCompatActivity() {
    lateinit var numTel: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Calendar dialog
        val birthDateEditText = findViewById<TextInputEditText>(R.id.NaissanceInput)

        val calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val selectedDate =
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
            birthDateEditText.setText(selectedDate)

        }

        birthDateEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val datePickerDialog = DatePickerDialog(
                    this, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.show()
            }
        }

        // sexe dropbox
        val genderOptions = listOf("M", "F")

        val genderAutoCompleteTextView =
            findViewById<AutoCompleteTextView>(R.id.genderAutoCompleteTextView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genderOptions)

        genderAutoCompleteTextView.setAdapter(adapter)

        val tel = intent.getStringExtra("tel")
        numTel = findViewById(R.id.TelInput)
        numTel.setText(tel)

        val registerBtn = findViewById<Button>(R.id.createClient)

        registerBtn.setOnClickListener {
            save()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun save() {
        val nom = findViewById<TextInputEditText>(R.id.NameInput)
        val prnom = findViewById<TextInputEditText>(R.id.PrenomInput)
        val naiss = findViewById<TextInputEditText>(R.id.NaissanceInput)
        val sex = findViewById<AutoCompleteTextView>(R.id.genderAutoCompleteTextView)
        val fachat = findViewById<TextInputEditText>(R.id.FachatInput)
        val qtier = findViewById<TextInputEditText>(R.id.VilleInput)
        val ville = findViewById<TextInputEditText>(R.id.QteInput)
        val pays = findViewById<TextInputEditText>(R.id.PaysInput)

        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = ClientRequest()
        request.nomClient = nom.text.toString().trim()
        request.prenomclient = prnom.text.toString().trim()
        request.dateClient = naiss.text.toString().trim()
        request.sexClient = sex.text.toString().trim()
        request.fachatclient = fachat.text.toString().trim()
        request.quartierclient = qtier.text.toString().trim()
        request.villeclient = ville.text.toString().trim()
        request.paysclient = pays.text.toString().trim()
        request.telcompte = numTel.text.toString().trim()
        api.createClient(
            request.nomClient,
            request.prenomclient,
            request.sexClient,
            request.dateClient,
            request.fachatclient,
            request.villeclient,
            request.quartierclient,
            request.paysclient,
            request.telcompte
        )?.enqueue(object : Callback<ClientResponse> {
            override fun onResponse(
                call: Call<ClientResponse>,
                response: Response<ClientResponse>
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

            override fun onFailure(call: Call<ClientResponse>, t: Throwable) {
                val message = t.localizedMessage
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })

}

}