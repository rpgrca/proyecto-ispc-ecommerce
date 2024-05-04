package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.User;

import java.util.function.Consumer;

public interface IApiServices {
    void login(String username, String password, Consumer<User> onSuccess, Runnable onError);
    void logout(Runnable onSuccess, Runnable onError);
}
