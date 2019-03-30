package com.example.muhammadjon.myproject.model;

public class BaseResponse<T> {

    private ApiError error;
    private  T success;

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public T getSuccess() {
        return success;
    }

    public void setSuccess(T success) {
        this.success = success;
    }
}