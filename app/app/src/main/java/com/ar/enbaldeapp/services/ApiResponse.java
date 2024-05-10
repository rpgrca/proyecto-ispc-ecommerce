package com.ar.enbaldeapp.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ApiResponse<T> {
    private final String jsonText;
    private final String message;
    private final T data;
    private final int status;

    public ApiResponse(String jsonText, boolean isJson) {
        if (isJson) {
            this.jsonText = jsonText;
            ServerApiResponse<T> response = new Gson().fromJson(jsonText, ServerApiResponse.class);
            this.message = response.getMessage();
            this.data = response.getData();
            this.status = response.getStatus();
        }
        else {
            this.jsonText = "";
            this.message = jsonText;
            this.data = null;
            this.status = 0;
        }
    }

    public T castResonseAs(Class<T> typeParameterClass)
    {
        JsonObject object = new Gson().toJsonTree(this.data).getAsJsonObject();
        return new Gson().fromJson(object, typeParameterClass);
        //return new Gson().fromJson(this.data, typeParameterClass);
        //return this.data;
    }

    public String getMessage() { return message; }
}
