package com.ar.enbaldeapp.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ApiError {
    private final String message;

    public ApiError(String text) {
        this.message = text;
    }

    public ApiError(JsonObject json) {
        ServerApiResponse error = new Gson().fromJson(json, ServerApiResponse.class);
        this.message = error.getMessage();
    }

    public String getMessage() { return message; }
}