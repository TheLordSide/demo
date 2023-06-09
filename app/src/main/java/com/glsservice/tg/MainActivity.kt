package com.glsservice.tg

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.glsservice.tg.Fragments.Discussion
import com.glsservice.tg.Fragments.Notification
import com.glsservice.tg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val discussionFragment = Discussion()
    private val notificationFragment = Notification()
    private var currentFragment: Fragment = discussionFragment // Fragment actuellement affiché
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Discussion())
        val role = intent.getStringExtra("role")
        val tel = intent.getStringExtra("tel")

        binding.bottomNavigationView.setOnItemSelectedListener setOnNavigationItemSelectedListener@{

            when(it.itemId){

                R.id.Discussions -> {
                    // Si le fragment de discussion est déjà affiché, ne pas le recharger
                    if (currentFragment is Discussion) {

                        return@setOnNavigationItemSelectedListener true

                    }
                    replaceFragment(discussionFragment)
                    currentFragment = discussionFragment
                    supportActionBar?.title = "Historique"
                }

                R.id.Notifications -> {
                    // Si le fragment de notification est déjà affiché, ne pas le recharger
                    if (currentFragment is Notification) {

                        return@setOnNavigationItemSelectedListener true

                    }
                    replaceFragment(notificationFragment)
                    currentFragment = notificationFragment
                    supportActionBar?.title = "Notifications"
                }

                else ->{

                }

            }

            true
        }

       /* val relative2 = findViewById<RelativeLayout>(R.id.relativeLayout2)
        relative1.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            intent.putExtra("role", role)
            intent.putExtra("tel", tel)
            startActivity(intent)
        }
        relative2.setOnClickListener {
            val intent = Intent(this, NotifyActivity::class.java)
            startActivity(intent)
        } */
    }

    override fun onBackPressed() {
        // laisser cette méthode vide pour bloquer le retour physique du bouton
    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }

}