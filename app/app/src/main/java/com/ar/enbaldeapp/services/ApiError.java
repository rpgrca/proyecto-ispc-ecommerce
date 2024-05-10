package com.ar.enbaldeapp.services;

import com.google.gson.Gson;

public class ApiError {
    private final String json;
    private final String message;

    public ApiError(String json, boolean isJson) {
        if (isJson) {
            this.json = json;
            ServerApiResponse error = new Gson().fromJson(json, ServerApiResponse.class);
            this.message = error.getMessage();
        }
        else {
            this.json = "";
            this.message = json;
        }
    }

    public String getMessage() { return message; }
}