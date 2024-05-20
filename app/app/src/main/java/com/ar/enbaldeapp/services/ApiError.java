package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.utilities.JsonUtilities;
import com.google.gson.JsonObject;

public class ApiError {
    private final String message;
    private final int status;
    private final Object data;

    public ApiError(String text) {
        this.message = text;
        this.status = 0;
        this.data = null;
    }

    public ApiError(JsonObject json) {
        ServerApiResponse error = JsonUtilities.getConfiguredGson().fromJson(json, ServerApiResponse.class);
        this.message = error.getMessage();
        this.status = error.getStatus();
        this.data = error.getData();
    }

    public String getMessage() { return message; }

    public int getStatus() { return status; }
}