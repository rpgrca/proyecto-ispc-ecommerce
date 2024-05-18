package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.UserToken;
import com.ar.enbaldeapp.models.utilities.HttpUtilities;
import com.ar.enbaldeapp.services.requesters.GetRequester;
import com.ar.enbaldeapp.services.requesters.NoBodyRequester;
import com.ar.enbaldeapp.services.requesters.PostRequester;
import com.ar.enbaldeapp.services.wrappers.ApiResponseWrapper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Consumer;

public class ApiServices implements IApiServices {
    // 10.0.2.2 es la ip de la maquina local corriendo el emulador de Android
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
    public void logout(Consumer<String> onSuccess, Consumer<ApiError> onError) {
        if (onSuccess == null) { throw new RuntimeException("El callback por éxito es inválido"); }
        if (onError == null) { throw new RuntimeException("El callback por fallo es inválido"); }

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

    @Override
    public void getCatalogue(Consumer<List<Product>> onSuccess, Consumer<ApiError> onError) {
        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onError == null) throw new RuntimeException("El callback por fallo es inválido");

        IServerConnector<Product> connector = getCatalogueFrom(ServerUrl + "/api/articulos");
        if (connector.connect()) {
            Type listType = new TypeToken<List<Product>>() {}.getType();
            List<Product> products = connector.getResponse().castResponseAsListOf(listType);
            onSuccess.accept(products);
        }
        else {
            onError.accept(connector.getError());
        }
    }

    protected IServerConnector<User> getUserFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostRequester(request));
    }

    protected IServerConnector<UserToken> getUserTokenFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostRequester(request));
    }

    protected IServerConnector<Boolean> disconnectFrom(String url) {
        return new ServerConnector<>(url, new NoBodyRequester());
    }

    protected IServerConnector<Product> getCatalogueFrom(String url) {
        return new ServerConnector<>(url, new GetRequester(new ApiResponseWrapper()));
    }
}

