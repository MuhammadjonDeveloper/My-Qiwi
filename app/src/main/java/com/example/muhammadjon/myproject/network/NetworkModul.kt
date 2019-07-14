package com.example.muhammadjon.myproject.network

import android.content.Context

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModul(context: Context) : INetworkManager {
    override val apiservice: ApiService

    init {
        //        OkHttpClient client=new OkHttpClient();
        //        client.networkInterceptors().add(new Interceptor() {
        //            @Override
        //            public Response intercept(Chain chain) throws IOException {
        //                Request.Builder rBuilder=chain.request().newBuilder();
        //                rBuilder.header("Content-Type","application/json");
        //                return chain.proceed(rBuilder.build());
        //            }
        //        });

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL1)
                //                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiservice = retrofit.create(ApiService::class.java)
    }
}
