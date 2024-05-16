package com.ar.enbaldeapp.services;

import android.hardware.ConsumerIrManager;

import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.UserToken;
import com.ar.enbaldeapp.models.utilities.HttpUtilities;
import com.ar.enbaldeapp.models.utilities.SharedPreferencesManager;

import java.util.function.Consumer;

public class ApiServices implements IApiServices {
    private static String ServerUrl = "http://10.0.2.2:8000";

    @Override
    public void login(String username, String password, Consumer<UserToken> onSuccess, Consumer<ApiError> onError) {
        if (username == null || username.trim().isEmpty()) {
            onError.accept(new ApiError(User.INVALID_USERNAME));
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            onError.accept(new ApiError(User.INVALID_PASSWORD));
            return;
        }

        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onError == null) throw new RuntimeException("El callback por fallo es inválido");

        ApiRequest request = new ApiRequest.Builder()
                .addContentDisposition("usuario", username)
                .addContentDisposition("clave", password)
                .Build();

        IServerConnector<UserToken> connector = getUserTokenFrom(ServerUrl + "/api/auth/login/", request);
        if (connector.connect()) {
            UserToken userToken = connector.getResponse().castResponseAs(UserToken.class);
            onSuccess.accept(userToken);
        }
        else {
            onError.accept(connector.getError());
        }


    }

    @Override
    public void logout(Consumer<String> onSuccess, Consumer<ApiError> onError)
    {
        IServerConnector<Boolean> connector = disconnectFrom(ServerUrl + "/api/auth/logout/");
        boolean result = false;

        if (connector.connect()) {
            result = HttpUtilities.isSuccessful(connector.getResponse().getStatus());
        }

        if (result) {
            onSuccess.accept(connector.getResponse().getMessage());
        }
        else {
            onError.accept(connector.getError());
        }
    }

    @Override
    public void register(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError) {
        try {
            new User(1, lastName, firstName, email, address, phoneNumber, "", username, password, User.Client);
        }
        catch (Exception ex) {
            onError.accept(new ApiError(ex.getMessage()));
            return;
        }

        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onError == null) throw new RuntimeException("El callback por fallo es inválido");

        ApiRequest request = new ApiRequest.Builder()
                .addContentDisposition("nombre", firstName)
                .addContentDisposition("apellido", lastName)
                .addContentDisposition("email", email)
                .addContentDisposition("direccion", address)
                .addContentDisposition("telefono", phoneNumber)
                .addContentDisposition("usuario", username)
                .addContentDisposition("clave", password)
                .addContentDisposition("tipo", User.Client)
                .addContentDisposition("observaciones", "")
                .Build();

        IServerConnector<User> connector = getUserFrom(ServerUrl + "/api/auth/signup/", request);
        if (connector.connect()) {
            User user = connector.getResponse().castResponseAs(User.class);
            onSuccess.accept(user);
        }
        else {
            onError.accept(connector.getError());
        }
    }

    protected IServerConnector<User> getUserFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostRequest(request));
    }

    protected IServerConnector<UserToken> getUserTokenFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostRequest(request));
    }

    protected IServerConnector<Boolean> disconnectFrom(String url) {
        return new ServerConnector<>(url, new NoBody());
    }
}