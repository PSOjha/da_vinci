package com.example.theapache64.da_vinci.api.responses;

import com.google.gson.annotations.SerializedName;

public class BaseAPIResponse<T> {

    @SerializedName("data")
    private final T data;

    @SerializedName("error")
    private final boolean error;

    @SerializedName("message")
    private final String message;


    public BaseAPIResponse(T data, boolean error, String message) {
        this.data = data;
        this.error = error;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
