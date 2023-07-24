package com.glsservice.tg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import tg.intaonline.intaonline.Model.GlobalVariables

class TrtansfertJournalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_trtansfert_journal)
        if (GlobalVariables.telAgent != null) {
            val editTel = findViewById<EditText>(R.id.TelInput)
            val telAgent = GlobalVariables.telAgent.toString()
            editTel.setText(telAgent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}