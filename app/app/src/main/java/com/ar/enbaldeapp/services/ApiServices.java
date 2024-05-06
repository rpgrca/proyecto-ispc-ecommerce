package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.User;

import java.util.function.Consumer;

public class ApiServices implements IApiServices {
    @Override
    public void login(String username, String password, Consumer<User> onSuccess, Runnable onError) {
        User user = new User(1, "Perez", "Juan", "juan.perez@gmail.com", "123 Main St Miami FL", "1234-5678", "Good client");
        onSuccess.accept(user);
    }

    @Override
    public void logout(Runnable onSuccess, Runnable onError) {
        onSuccess.run();
    }
}

