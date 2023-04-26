package com.example.demo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val relative1 = findViewById<RelativeLayout>(R.id.relativeLayout1)
        relative1.setOnClickListener {
            val intent = Intent(this, AdminNotifyActivity::class.java)
            startActivity(intent)
        }
    }
}