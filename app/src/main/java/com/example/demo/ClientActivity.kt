package com.example.demo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val role = intent.getStringExtra("role")
        val tel = intent.getStringExtra("tel")
        println(role)
        println(tel)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Calendar dialog
        val birthDateEditText = findViewById<TextInputEditText>(R.id.NaissanceInput)

        val calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val selectedDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(calendar.time)
            birthDateEditText.setText(selectedDate)
        }

        birthDateEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val datePickerDialog = DatePickerDialog(this, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()
            }
        }

        // sexe dropbox
        val genderOptions = listOf("M", "F")

        val genderAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.genderAutoCompleteTextView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genderOptions)

        genderAutoCompleteTextView.setAdapter(adapter)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}