package com.ar.enbaldeapp.services;

import android.os.Handler;
import android.os.Looper;

import com.ar.enbaldeapp.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    private static String ServerUrl = "http://10.0.2.2:8000";
    private static String ContentDispositionSeparator = "-----------------------------316364238710708646983401506042";

    @Override
    public void login(String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError) {
        User user = new User(1, "Perez", "Juan", "juan.perez@gmail.com", "123 Main St Miami FL", "1234-5678", "Good client", "juan", "12345678");
        onSuccess.accept(user);
    }

    @Override
    public void logout(Runnable onSuccess, Consumer<ApiError> onError)
    {

        onSuccess.run();
    }

    @Override
    public void register(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError) {
        StringBuilder stringBuilder = new StringBuilder();
        addContentDisposition(stringBuilder, "nombre", firstName);
        addContentDisposition(stringBuilder, "apellido", lastName);
        addContentDisposition(stringBuilder, "email", email);
        addContentDisposition(stringBuilder, "direccion", address);
        addContentDisposition(stringBuilder, "telefono", phoneNumber);
        addContentDisposition(stringBuilder, "usuario", username);
        addContentDisposition(stringBuilder, "clave", password);
        addContentDisposition(stringBuilder, "tipo", "2");
        addContentDisposition(stringBuilder, "observaciones", "");
        addSeparator(stringBuilder);

        ApiRequest request = new ApiRequest(stringBuilder.toString());
        ServerConnector<User> connector = new ServerConnector<User>(ServerUrl + "/api/auth/signup/", request);
        if (connector.connect()) {
            User user = connector.getResponse().castResonseAs(User.class);
            onSuccess.accept(user);
        }
        else {
            onError.accept(connector.getError());
        }
    }

    private void addContentDisposition(StringBuilder stringBuilder, String key, String value) {
        addSeparator(stringBuilder);
        stringBuilder.append("Content-Disposition: form-data; name=" + "\"" + key + "\"\r\n\r\n");
        stringBuilder.append(value + "\r\n");
    }

    private void addSeparator(StringBuilder stringBuilder) {
        stringBuilder.append(ContentDispositionSeparator + "\r\n");
    }
}

