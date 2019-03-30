package com.example.muhammadjon.myproject.network;

import com.example.muhammadjon.myproject.dbase.MyPojo;
import com.example.muhammadjon.myproject.model.req.SignInRequest;
import com.example.muhammadjon.myproject.model.req.SignUpRequest;
import com.example.muhammadjon.myproject.model.res.SignInResponse;
import com.example.muhammadjon.myproject.model.res.SignUpResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST("sign_in")
    Single<SignInResponse> signIn(@Body SignInRequest request);


    @POST("sign_up")
    Single<SignUpResponse> signUp(@Body SignUpRequest request);

    //    @Headers("Content-Type: application/json")
    @Headers("Accept: application/json")
    @GET("all")
    Single<MyPojo> event();

    //944989927
}
