package com.glsservice.tg

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.glsservice.tg.Apiclient.ApiRequest.JournalTransfertRequest
import com.glsservice.tg.Apiclient.ApiResponse.AnswerResponse
import com.glsservice.tg.Apiclient.Service.ApiClient
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tg.intaonline.intaonline.ApiClient.service.ApiInterface
import tg.intaonline.intaonline.Model.GlobalVariables
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TrtansfertJournalActivity : AppCompatActivity() {
    private lateinit var editTel : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_trtansfert_journal)
        editTel = findViewById(R.id.TelInput)
        if (GlobalVariables.telAgent != null) {
            val telAgent = GlobalVariables.telAgent.toString()
            editTel.setText(telAgent)
        }

        val registerBtn = findViewById<Button>(R.id.transfert)
        registerBtn.setOnClickListener {
            transfert()
        }
        dateSetup()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Journaux -> {
               // val intent = Intent(applicationContext, ::class.java)
               // startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun transfert(){

        val userTel = findViewById<EditText>(R.id.TelInput)
        val date = findViewById<EditText>(R.id.transfertDate)
        val qte = findViewById<EditText>(R.id.qteLayoutInput)
        val errorDate = findViewById<TextInputLayout>(R.id.datePickerInputLayout)
        val errorUserTel = findViewById<TextInputLayout>(R.id.Tel)
        val errorQte = findViewById<TextInputLayout>(R.id.qteLayout)

        if (userTel.text.toString().trim().isEmpty()) {
            errorUserTel.error = getString(R.string.telVide)
            return
        } else {
            errorUserTel.isErrorEnabled = false
        }

        if (date.text.toString().trim().isEmpty()) {
            errorDate.error = "Date requise"
            return
        } else {
            errorDate.isErrorEnabled = false
        }

        if (qte.text.toString().trim().isEmpty()) {
            errorQte.error = "Quantité requise"
            return
        } else {
            errorQte.isErrorEnabled = false
        }

        val api = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val request = JournalTransfertRequest()
        request.Quantity = qte.text.toString().trim()
        request.numagent = userTel.text.toString().trim()
        request.DateTransfert = GlobalVariables.dateTransfert
        api.transfert(request.numagent, request.DateTransfert, request.Quantity).enqueue(object : Callback<AnswerResponse> {
            override fun onResponse(
                call: Call<AnswerResponse>,
                response: Response<AnswerResponse>
            ) {
                val message = response.body()?.message
                val success = response.body()?.success

                if (response.isSuccessful) {

                    if (success == "true") {
                        // A NE PAS TOUCHER
                        Toast.makeText(applicationContext, message.toString(), Toast.LENGTH_LONG).show()
                        userTel.text.clear()
                        date.text.clear()
                        qte.text.clear()
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



    private fun dateSetup() {

        val birthDateEditText = findViewById<TextInputEditText>(R.id.transfertDate)

        val calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val selectedDate =
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
            birthDateEditText.setText(selectedDate)
            GlobalVariables.dateTransfert = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
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
    }

}