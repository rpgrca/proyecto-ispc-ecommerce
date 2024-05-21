package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.Cart;
import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.Selection;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.UserToken;
import com.ar.enbaldeapp.models.utilities.HttpUtilities;
import com.ar.enbaldeapp.services.connection.HttpUrlConnectionWrapper;
import com.ar.enbaldeapp.services.connection.IHttpUrlConnectionWrapper;
import com.ar.enbaldeapp.services.requesters.AuthenticatedGetRequester;
import com.ar.enbaldeapp.services.requesters.GetRequester;
import com.ar.enbaldeapp.services.requesters.NoBodyRequester;
import com.ar.enbaldeapp.services.requesters.PostFormDataRequester;
import com.ar.enbaldeapp.services.requesters.PutRequester;
import com.ar.enbaldeapp.services.wrappers.ApiResponseWrapper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ApiServices implements IApiServices {
    // 10.0.2.2 es la ip de la maquina local corriendo el emulador de Android
    private static String ServerUrl = "http://10.0.2.2:8000";

    private final Callable<IHttpUrlConnectionWrapper> connectionFactory;

    public ApiServices() {
        this.connectionFactory = HttpUrlConnectionWrapper::new;
    }

    public ApiServices(Callable<IHttpUrlConnectionWrapper> connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void login(String username, String password, Consumer<UserToken> onSuccess, Consumer<ApiError> onFailure) {
        if (username == null || username.trim().isEmpty()) {
            onFailure.accept(new ApiError(User.INVALID_USERNAME));
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            onFailure.accept(new ApiError(User.INVALID_PASSWORD));
            return;
        }

        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onFailure == null) throw new RuntimeException("El callback por fallo es inválido");

        ApiRequest request = new ApiRequest.Builder()
                .addContentDisposition("usuario", username)
                .addContentDisposition("clave", password)
                .buildAsUrlEncodedData();

        IServerConnector<UserToken> connector = getUserTokenFrom(ServerUrl + "/api/auth/login/", request);
        if (connector.connect()) {
            UserToken userToken = connector.getResponse().castResponseAs(UserToken.class);
            onSuccess.accept(userToken);
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    @Override
    public void logout(String accessToken, Consumer<String> onSuccess, Consumer<ApiError> onFailure) {
        if (accessToken == null || accessToken.trim().isEmpty()) { throw new RuntimeException("El access token es inválido"); }
        if (onSuccess == null) { throw new RuntimeException("El callback por éxito es inválido"); }
        if (onFailure == null) { throw new RuntimeException("El callback por fallo es inválido"); }

        IServerConnector<Boolean> connector = disconnectFrom(ServerUrl + "/api/auth/logout/", accessToken);
        boolean result = false;

        if (connector.connect()) {
            result = HttpUtilities.isSuccessful(connector.getResponse().getStatus());
        }

        if (result) {
            onSuccess.accept(connector.getResponse().getMessage());
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    @Override
    public void register(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onFailure) {
        try {
            new User(1, lastName, firstName, email, address, phoneNumber, "", username, password, User.Client);
        }
        catch (Exception ex) {
            onFailure.accept(new ApiError(ex.getMessage()));
            return;
        }

        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onFailure == null) throw new RuntimeException("El callback por fallo es inválido");

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
                .buildAsUrlEncodedData();

        IServerConnector<User> connector = getUserFrom(ServerUrl + "/api/auth/signup/", request);
        if (connector.connect()) {
            User user = connector.getResponse().castResponseAs(User.class);
            onSuccess.accept(user);
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    @Override
    public void getCatalogue(Consumer<List<Product>> onSuccess, Consumer<ApiError> onFailure) {
        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onFailure == null) throw new RuntimeException("El callback por fallo es inválido");

        IServerConnector<Product> connector = getCatalogueFrom(ServerUrl + "/api/articulos");
        if (connector.connect()) {
            Type listType = new TypeToken<List<Product>>() {}.getType();
            List<Product> products = connector.getResponse().castResponseAsListOf(listType);
            onSuccess.accept(products);
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    @Override
    public void getCart(String accessToken, long cartId, Consumer<Cart> onSuccess, Consumer<ApiError> onFailure) {
        if (accessToken == null || accessToken.trim().isEmpty()) throw new RuntimeException("El access token es inválido");
        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onFailure == null) throw new RuntimeException("El callback por fallo es inválido");

        IServerConnector<Selection> connector = getCart(ServerUrl + "/api/carritos/" + cartId, accessToken);
        if (connector.connect()) {
            Type listType = new TypeToken<List<Selection>>() {}.getType();
            List<Selection> selections = connector.getResponse().castResponseAsListOf(listType);
            onSuccess.accept(new Cart(cartId, selections));
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    @Override
    public void addToCart(String accessToken, Cart cart, Product product, int amount, Consumer<Selection> onSuccess, Consumer<ApiError> onFailure) {
        if (accessToken == null || accessToken.trim().isEmpty()) throw new RuntimeException("El access token es inválido");
        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onFailure == null) throw new RuntimeException("El callback por fallo es inválido");

        CartModificationRequest cartModificationRequest = new CartModificationRequest(product, amount);
        ApiRequest apiRequest = new ApiRequest.Builder()
                .addBody(cartModificationRequest)
                .addAccessToken(accessToken)
                .buildAsBody();
        IServerConnector<Selection> connector = getModifiedCartFrom(ServerUrl + "/api/carritos/" + cart.getId(), apiRequest);
        if (connector.connect()) {
            Selection selection = connector.getResponse().castResponseAs(Selection.class);
            onSuccess.accept(selection);
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    protected IServerConnector<Selection> getModifiedCartFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PutRequester<>(request, new ApiResponseWrapper()), this.connectionFactory);
    }

    protected IServerConnector<User> getUserFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostFormDataRequester<>(request), this.connectionFactory);
    }

    protected IServerConnector<UserToken> getUserTokenFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostFormDataRequester<>(request), this.connectionFactory);
    }

    protected IServerConnector<Boolean> disconnectFrom(String url, String accessToken) {
        return new ServerConnector<>(url, new NoBodyRequester<>(accessToken), this.connectionFactory);
    }

    protected IServerConnector<Product> getCatalogueFrom(String url) {
        return new ServerConnector<>(url, new GetRequester<>(new ApiResponseWrapper()), this.connectionFactory);
    }

    protected IServerConnector<Selection> getCart(String url, String accessToken) {
        return new ServerConnector<>(url, new AuthenticatedGetRequester<>(new ApiResponseWrapper(), accessToken), this.connectionFactory);
    }
}