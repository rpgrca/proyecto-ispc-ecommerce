package com.ar.enbaldeapp.services;

import com.google.gson.Gson;

public class ApiRequest {
    private Gson json;

    public ApiRequest(Gson json) {
        this.json = json;
    }

    public Gson getJson() {
        return this.json;
    }
}
