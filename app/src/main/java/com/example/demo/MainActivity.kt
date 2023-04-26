package com.example.demo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val role = intent.getStringExtra("role")
        val tel = intent.getStringExtra("tel")
        println(role)
        println(tel)
        val relative1 = findViewById<RelativeLayout>(R.id.relativeLayout1)
        val relative2 = findViewById<RelativeLayout>(R.id.relativeLayout2)
        relative1.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            intent.putExtra("role", role)
            intent.putExtra("tel", tel)
            startActivity(intent)
        }
        relative2.setOnClickListener {
            val intent = Intent(this, NotifyActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        // laisser cette méthode vide pour bloquer le retour physique du bouton
    }
}