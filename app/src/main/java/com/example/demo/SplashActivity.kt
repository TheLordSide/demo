package com.example.demo

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //set splashscreen to fullscreen
        //animate the logo and launch loginscreen
        val imageID=findViewById<View>(R.id.imageID)
        imageID.alpha= 0f
        imageID.animate().setDuration(5000).alpha(1f).withEndAction{
            val oi =  Intent(this, LoginActivity::class.java)
            startActivity(oi)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}