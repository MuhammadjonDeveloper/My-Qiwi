package com.example.muhammadjon.myproject.activities

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
import com.example.muhammadjon.myproject.model.req.SignInRequest
import com.example.muhammadjon.myproject.model.res.SignInResponse
import com.example.muhammadjon.myproject.network.ApiService
import com.example.muhammadjon.myproject.utils.NetUtils

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, IWatcher {
    private var pBar: ProgressBar? = null
    private var login: String? = null
    private var passwort: String? = null
    private var service: ApiService? = null
    private val cd = CompositeDisposable()
    private var preferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorYellow)
        }

        progressBar_login!!.visibility = View.INVISIBLE
        login_l_login.addTextChangedListener(TextWatcherImpl(this))
        login_l_passwort.addTextChangedListener(TextWatcherImpl(this))
        login_l_sing_up_btn.setOnClickListener(this)
        login_l_btn_save.setOnClickListener(this)
        val app = application as App
        service = app.apiService
        login_l_btn_save!!.isEnabled = false
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_l_btn_save -> {
                val netUtils = NetUtils.getINSTAINS(this)
                if (netUtils.isOnline) {
                    pBar!!.visibility = View.VISIBLE
                    val request = SignInRequest(login!!, passwort!!)
                    val single = service!!.signIn(request)
                    single.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : SingleObserver<SignInResponse> {
                                override fun onSubscribe(d: Disposable) {
                                    cd.add(d)
                                }

                                override fun onSuccess(signInResponse: SignInResponse) {
                                    val num = preferences!!.getInt("iss", 9)
                                    Toast.makeText(this@LoginActivity, "userId : " + signInResponse.userId, Toast.LENGTH_SHORT).show()
                                    if (num == signInResponse.userId) {
                                        val intent = Intent(this@LoginActivity, PingcodeActivity::class.java)
                                        pBar!!.visibility = View.GONE
                                        startActivity(intent)
                                        finish()
                                    }
                                }

                                override fun onError(e: Throwable) {
                                    Log.d("LoginActivity", "onError" + e.message)
                                }
                            })
                } else {
                    Toast.makeText(this, "not network connected", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.login_l_sing_up_btn -> {
                val intent = Intent(this@LoginActivity, RegistrActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (cd != null && cd.isDisposed) {
            cd.dispose()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onTextChanged(text: String) {
        login = login_l_login.text!!.toString()
        passwort = login_l_passwort!!.text!!.toString()
        if (!login!!.isEmpty() && !passwort!!.isEmpty()) {
            login_l_btn_save!!.isEnabled = true
            login_l_btn_save!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorYellow))
        } else {
            login_l_btn_save!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.divider_color))
            login_l_btn_save!!.isEnabled = false
        }
    }
}
