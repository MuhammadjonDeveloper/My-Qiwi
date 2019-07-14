package com.example.muhammadjon.myproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TextInputEditText
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast

import com.example.muhammadjon.myproject.App
import com.example.muhammadjon.myproject.R
import com.example.muhammadjon.myproject.comon.IWatcher
import com.example.muhammadjon.myproject.comon.TextWatcherImpl
import com.example.muhammadjon.myproject.model.req.SignUpRequest
import com.example.muhammadjon.myproject.model.res.SignUpResponse
import com.example.muhammadjon.myproject.network.ApiService
import com.example.muhammadjon.myproject.utils.NetUtils

import java.util.Objects

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.actictivity_register.*
import kotlinx.android.synthetic.main.activity_login.*

class RegistrActivity : AppCompatActivity(), View.OnClickListener, IWatcher {
    private var service: ApiService? = null
    private var preferences: SharedPreferences? = null
    private var surname: String? = null
    private var name: String? = null
    private var login: String? = null
    private var passwort: String? = null
    private val cd = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actictivity_register)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorYellow)
        }

        progressBar1!!.visibility = View.INVISIBLE
        register_l_btn!!.setOnClickListener(this)
        val app = application as App
        service = app.apiService

        register_l_btn!!.isEnabled = false
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        register_l_lastname!!.addTextChangedListener(TextWatcherImpl(this))
        register_l_firstname!!.addTextChangedListener(TextWatcherImpl(this))
        register_activity_l_login!!.addTextChangedListener(TextWatcherImpl(this))
        register_l_passwort!!.addTextChangedListener(TextWatcherImpl(this))
    }

    override fun onClick(view: View) {
        val netUtils = NetUtils.getINSTAINS(this)
        if (netUtils.isOnline) {
            progressBar1!!.visibility = View.VISIBLE
            Log.d("RegistrActivity", surname + name + "Salomlar")
            if (!surname!!.isEmpty() && !name!!.isEmpty() &&
                    !login!!.isEmpty() && !passwort!!.isEmpty()) {
                val signUpRequest = SignUpRequest(surname!!, name!!, login!!, passwort!!)
                val sign = service!!.signUp(signUpRequest)
                sign.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : SingleObserver<SignUpResponse> {
                            override fun onSubscribe(d: Disposable) {
                                cd.add(d)
                            }
                            override fun onSuccess(signUpResponse: SignUpResponse) {
                                Log.d("MainActivity", "Succes" + signUpResponse.userId)
                                preferences!!.edit().putInt("iss", signUpResponse.userId).apply()
                                val intent = Intent(this@RegistrActivity,
                                        PingcodeActivity::class.java)
                                Toast.makeText(this@RegistrActivity, "userId : " + signUpResponse.userId, Toast.LENGTH_SHORT).show()
                                preferences!!.edit().putBoolean("is_first", false).apply()
                                intent.putExtra("key", passwort)
                                startActivity(intent)
                                finish()
                            }

                            override fun onError(e: Throwable) {
                                Log.d("RegisterActivity", "onError" + e.message)
                            }
                        })

            }
        } else {
            Toast.makeText(this, "network not connected", Toast.LENGTH_SHORT).show()
        }

    }

    @SuppressLint("ResourceAsColor")
    override fun onTextChanged(text: String) {
        surname = Objects.requireNonNull(register_l_firstname!!.text).toString()
        name = Objects.requireNonNull(register_l_lastname!!.text).toString()
        login = Objects.requireNonNull(register_activity_l_login!!.text).toString()
        passwort = Objects.requireNonNull(register_l_passwort!!.text).toString()
        if (!surname!!.isEmpty() && !name!!.isEmpty() &&
                !login!!.isEmpty() && !passwort!!.isEmpty()) {
            register_l_btn!!.isEnabled = true
            register_l_btn!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorYellow))

        } else {


            register_l_btn!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.divider_color))
            register_l_btn!!.isEnabled = false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (cd != null && cd.isDisposed) {
            cd.dispose()
        }
    }

}
