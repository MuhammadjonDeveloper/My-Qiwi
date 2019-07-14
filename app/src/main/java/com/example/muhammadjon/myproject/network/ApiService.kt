package com.example.muhammadjon.myproject.network

import com.example.muhammadjon.myproject.dbase.MyPojo
import com.example.muhammadjon.myproject.model.req.SignInRequest
import com.example.muhammadjon.myproject.model.req.SignUpRequest
import com.example.muhammadjon.myproject.model.res.SignInResponse
import com.example.muhammadjon.myproject.model.res.SignUpResponse

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("sign_in")
    fun signIn(@Body request: SignInRequest): Single<SignInResponse>


    @POST("sign_up")
    fun signUp(@Body request: SignUpRequest): Single<SignUpResponse>

    //    @Headers("Content-Type: application/json")
    @Headers("Accept: application/json")
    @GET("all")
    fun event(): Single<MyPojo>

    //944989927
}
