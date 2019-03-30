package com.example.muhammadjon.myproject;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.muhammadjon.myproject.activities.LoginActivity;
import com.example.muhammadjon.myproject.activities.MainActivity;
import com.example.muhammadjon.myproject.activities.RegistrActivity;
import com.example.muhammadjon.myproject.network.ApiService;
import com.example.muhammadjon.myproject.network.NetworkManagerImpl;

public class App extends Application {
    private ApiService apiService;
    private SharedPreferences preferences;

    //<!--style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"-->
    @Override
    public void onCreate() {
        super.onCreate();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        NetworkManagerImpl manager = new NetworkManagerImpl(this);
        apiService = manager.getApiservice();

//        startActivity(new Intent(this, MainActivity.class));

        boolean isFirst = preferences.getBoolean("is_first", true);
        if (isFirst) {
            startActivity(new Intent(this, RegistrActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
    public ApiService getApiService() {
        return apiService;
    }
}
