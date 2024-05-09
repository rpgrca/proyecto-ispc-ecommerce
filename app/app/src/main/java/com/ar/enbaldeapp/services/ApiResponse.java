package com.ar.enbaldeapp.services;

import com.google.gson.Gson;

public class ApiResponse<T> {
    private final String jsonText;

    public ApiResponse(String jsonText) {
        this.jsonText = jsonText;
    }

    public T castResonseAs(Class<T> typeParameterClass)
    {
        return new Gson().fromJson(jsonText, typeParameterClass);
    }
}
