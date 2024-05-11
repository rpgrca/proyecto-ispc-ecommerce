package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.User;

import java.util.function.Consumer;

public class ApiServices implements IApiServices {
    private static String ServerUrl = "http://10.0.2.2:8000";

    @Override
    public void login(String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError) {
        // TODO: Agregar chequeos para cada parametro, llamar al servidor
        User user = new User(1, "Perez", "Juan", "juan.perez@gmail.com", "123 Main St Miami FL", "1234-5678", "Good client", "juan", "12345678");
        onSuccess.accept(user);
    }

    @Override
    public void logout(Runnable onSuccess, Consumer<ApiError> onError)
    {
        // TODO: Llamar al servidor
        onSuccess.run();
    }

    @Override
    public void register(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError) {
        try {
            new User(1, lastName, firstName, email, address, phoneNumber, "", username, password);
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
                .addContentDisposition("tipo", "2")
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
        return new ServerConnector<>(url, request);
    }
}