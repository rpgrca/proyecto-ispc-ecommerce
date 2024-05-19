package com.ar.enbaldeapp.services;

import com.google.gson.annotations.SerializedName;

public class ServerApiResponse {
    @SerializedName("mensaje")
    private String message;
    private Object data;
    private int status;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
