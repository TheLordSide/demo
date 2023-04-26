package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val role = intent.getStringExtra("role")
        val tel = intent.getStringExtra("tel")
        println(role)
        println(tel)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}