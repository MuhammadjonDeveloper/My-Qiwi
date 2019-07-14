package com.example.muhammadjon.myproject

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.example.muhammadjon.myproject.network.ApiService
import com.example.muhammadjon.myproject.network.NetworkManagerImpl

class App : Application() {
    var apiService: ApiService? = null
        private set
    private var preferences: SharedPreferences? = null

    //<!--style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"-->
    override fun onCreate() {
        super.onCreate()
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val manager = NetworkManagerImpl(this)
        apiService = manager.apiservice

        //        startActivity(new Intent(this, MainActivity.class));

    }
}
