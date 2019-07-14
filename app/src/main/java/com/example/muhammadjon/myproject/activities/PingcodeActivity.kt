package com.example.muhammadjon.myproject.activities

import android.annotation.TargetApi
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView

import com.example.muhammadjon.myproject.R
import com.example.muhammadjon.myproject.component.PinCircles
import com.example.muhammadjon.myproject.component.PinKeyboard

class PingcodeActivity : AppCompatActivity() {
    private val pinContainer: LinearLayout? = null
    //    private LottieAnimationView animationView;
    private var pinInfoText: TextView? = null
    private var circles: PinCircles? = null
    private var pinKeyboard: PinKeyboard? = null
    private var animation: Animation? = null

    private var preferences: SharedPreferences? = null
    private var pinCode = ""
    private val tempPin: String? = null
    private var createPinCode: Boolean? = null
    private var pass: String? = null
    private var counter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val arg = intent.extras
        if (arg != null) {
            pass = arg.getString("key")
        }
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        createPinCode = preferences!!.getBoolean("is_counter", true)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorYellow)
        }


        animation = AnimationUtils.loadAnimation(this, R.anim.shake)

        pinKeyboard = findViewById(R.id.pin_keyboard)
        circles = findViewById(R.id.pin_circles)
        pinInfoText = findViewById(R.id.pin_info_text)

        pinKeyboard!!.setOnTabListener(object : PinKeyboard.TabListener {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onNumberTab(number: String) {
                setPinCode(pinCode + number)
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onEraseTab() {
                if (pinCode.length > 0) {
                    setPinCode(pinCode.substring(0, pinCode.length - 1))
                }
            }
        })
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private fun setPinCode(pinCode: String) {
        if (pinCode.length <= 4) {
            this.pinCode = pinCode
        }
        circles!!.fillCircles(this.pinCode.length)
        if (this.pinCode.length == 4) {
            Handler().postDelayed({
                if (this.pinCode.length == 4)
                    checkPin()

            }, 200)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private fun checkPin() {
        if (createPinCode!!) {
            if (counter == 2) {
                preferences!!.edit().putBoolean("is_counter", false).apply()
                preferences!!.edit().putString("is_correct", pinCode).apply()
                startActivity(Intent(this@PingcodeActivity, MainActivity::class.java))
                finish()
            } else {
                Handler().postDelayed({ setPinCode("") }, 1000)
                counter++
            }
        } else {
            if (pinCode == preferences!!.getString("is_correct", pinCode)) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                pinInfoText!!.text = "Pin code error"
                circles!!.startAnimation(animation)
                setPinCode("")
            }
        }

    }
}
