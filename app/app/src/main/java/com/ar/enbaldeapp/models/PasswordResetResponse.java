package com.ar.enbaldeapp.models;

public class PasswordResetResponse {
    private final String status;

    public PasswordResetResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
