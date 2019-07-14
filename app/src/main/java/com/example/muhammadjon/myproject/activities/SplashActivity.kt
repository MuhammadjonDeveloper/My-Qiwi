package com.example.muhammadjon.myproject.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private var preferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        Handler().postDelayed({
            val isFirst = preferences!!.getBoolean("is_first", true)
            if (isFirst) {
                startActivity(Intent(this@SplashActivity, RegistrActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }, 1000)
    }
}
