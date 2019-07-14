package com.example.muhammadjon.myproject.model

class BaseResponse<T> {

    var error: ApiError? = null
    var success: T? = null
}