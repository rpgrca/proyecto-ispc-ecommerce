package com.ar.enbaldeapp.ui.login;

import com.ar.enbaldeapp.models.User;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private User model;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(User model) {
        this.model = model;
    }

    User getModel() {
        return model;
    }

    public String getDisplayName() {
        return this.model.getFirstName() + " " + this.model.getLastName();
    }
}