package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.utilities.JsonUtilities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.ToNumberPolicy;

public class ApiResponse<T> {
    private final String message;
    private final T data;
    private final int status;

    public ApiResponse(String jsonText, boolean isJson) {
        if (isJson) {
            ServerApiResponse<T> response = JsonUtilities.getConfiguredGson().fromJson(jsonText, ServerApiResponse.class);
            this.message = response.getMessage();
            this.data = response.getData();
            this.status = response.getStatus();
        }
        else {
            this.message = jsonText;
            this.data = null;
            this.status = 0;
        }
    }

    public T castResponseAs(Class<T> typeParameterClass)
    {
        JsonObject object = new Gson().toJsonTree(this.data).getAsJsonObject();
        return new Gson().fromJson(object, typeParameterClass);
    }

    public String getMessage() { return message; }
}
