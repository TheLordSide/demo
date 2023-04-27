package com.example.demo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import com.example.demo.Apiclient.ApiResponse.ClientList

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
        val relative2 = findViewById<RelativeLayout>(R.id.relativeLayout2)
        relative2.setOnClickListener {
            val intent = Intent(this, ClientListeActivity::class.java)
            startActivity(intent)
        }
        val relative3 = findViewById<RelativeLayout>(R.id.relativeLayout3)
        relative3.setOnClickListener {
            val intent = Intent(this, CompteListeActivity::class.java)
            startActivity(intent)
        }
        val relative4 = findViewById<RelativeLayout>(R.id.relativeLayout4)
        relative4.setOnClickListener {
            val intent = Intent(this, NotifyActivity::class.java)
            startActivity(intent)
        }

    }
}