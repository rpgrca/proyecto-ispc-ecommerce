package com.ar.enbaldeapp.services;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class ServerApiResponse {
    @SerializedName("mensaje")
    private String message;
    private LinkedTreeMap<String, Object> data;
    private int status;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(LinkedTreeMap<String, Object> data) {
        this.data = data;
    }

    public LinkedTreeMap<String, Object> getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
