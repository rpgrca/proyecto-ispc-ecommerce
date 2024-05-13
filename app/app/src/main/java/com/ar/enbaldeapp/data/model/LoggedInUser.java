package com.ar.enbaldeapp.data.model;

import com.ar.enbaldeapp.models.User;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {
    private User model;

    public LoggedInUser(User model) {
        this.model = model;
    }

    public User getModel() { return model; }
}