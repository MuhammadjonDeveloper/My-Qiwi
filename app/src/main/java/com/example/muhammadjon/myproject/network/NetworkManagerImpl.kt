package com.example.muhammadjon.myproject.network

import android.content.Context

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManagerImpl(context: Context) : INetworkManager {
    override val apiservice: ApiService

    init {

        val client = OkHttpClient.Builder()
                //                .addInterceptor(new ChuckInterceptor(context))
                .build()
        
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL2)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiservice = retrofit.create(ApiService::class.java)
    }
}
