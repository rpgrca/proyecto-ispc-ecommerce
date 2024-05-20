package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.utilities.JsonUtilities;
import com.google.gson.JsonObject;

public class ApiError {
    private final String message;

    public ApiError(String text) {
        this.message = text;
    }

    public ApiError(JsonObject json) {
        ServerApiResponse error = JsonUtilities.getConfiguredGson().fromJson(json, ServerApiResponse.class);
        this.message = error.getMessage();
    }

    public String getMessage() { return message; }
}