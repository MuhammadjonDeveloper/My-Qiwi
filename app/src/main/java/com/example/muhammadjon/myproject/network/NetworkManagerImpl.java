package com.example.muhammadjon.myproject.network;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManagerImpl implements INetworkManager {
    private ApiService service;

    public NetworkManagerImpl(Context context) {

        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new ChuckInterceptor(context))
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL2)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    @Override
    public ApiService getApiservice() {
        return service;
    }
}
