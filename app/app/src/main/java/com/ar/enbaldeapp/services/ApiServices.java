package com.ar.enbaldeapp.services;

import android.os.Handler;
import android.os.Looper;

import com.ar.enbaldeapp.models.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class ApiServices implements IApiServices {
    @Override
    public void login(String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError) {
        User user = new User(1, "Perez", "Juan", "juan.perez@gmail.com", "123 Main St Miami FL", "1234-5678", "Good client");
        onSuccess.accept(user);
    }

    @Override
    public void logout(Runnable onSuccess, Consumer<ApiError> onError)
    {

        onSuccess.run();
    }

    @Override
    public void register(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError) {

    }
}

