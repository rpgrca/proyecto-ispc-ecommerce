package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.Cart;
import com.ar.enbaldeapp.models.HistoryResponse;
import com.ar.enbaldeapp.models.PasswordResetRequest;
import com.ar.enbaldeapp.models.PasswordResetResponse;
import com.ar.enbaldeapp.models.ResetTokenRequest;
import com.ar.enbaldeapp.models.ResetTokenResponse;
import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.Sale;
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
import com.ar.enbaldeapp.services.requesters.PostRequester;
import com.ar.enbaldeapp.services.requesters.PutRequester;
import com.ar.enbaldeapp.services.wrappers.ApiResponseWrapper;
import com.ar.enbaldeapp.services.wrappers.DjangoApiResetPasswordResponseWrapper;
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
    public String getUrl() {
        return ServerUrl;
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

        IServerConnector<UserToken> connector = getUserTokenFrom(getUrl() + "/api/auth/login/", request);
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

        IServerConnector<Boolean> connector = disconnectFrom(getUrl() + "/api/auth/logout/", accessToken);
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

        IServerConnector<User> connector = getUserFrom(getUrl() + "/api/auth/signup/", request);
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

        IServerConnector<Product> connector = getCatalogueFrom(getUrl() + "/api/articulos");
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

        IServerConnector<Selection> connector = getCart(getUrl() + "/api/carritos/" + cartId, accessToken);
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
        IServerConnector<Selection> connector = getModifiedCartFrom(getUrl() + "/api/carritos/" + cart.getId(), apiRequest);
        if (connector.connect()) {
            Selection selection = connector.getResponse().castResponseAs(Selection.class);
            onSuccess.accept(selection);
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    @Override
    public void sendRecoveryToken(String email, Consumer<String> onSuccess, Consumer<ApiError> onFailure) {
        if (email == null || email.trim().isEmpty()) {
            onFailure.accept(new ApiError(User.INVALID_EMAIL));
            return;
        }

        if (onSuccess == null) throw new RuntimeException("El callback por éxito es inválido");
        if (onFailure == null) throw new RuntimeException("El callback por fallo es inválido");

        ApiRequest request = new ApiRequest.Builder()
                .addBody(new ResetTokenRequest(email))
                .buildAsBody();

        IServerConnector<ResetTokenResponse> connector = getTokenResetFrom(getUrl() + "/api/auth/password_reset/", request);
        if (connector.connect()) {
            ResetTokenResponse response = connector.getResponse().castResponseAs(ResetTokenResponse.class);
            onSuccess.accept(response.getStatus());
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    public static final String INVALID_TOKEN = "El token es inválido";

    public void resetPassword(String accessToken, String newPassword, Consumer<String> onSuccess, Consumer<ApiError> onFailure) {
        if (accessToken == null || accessToken.trim().isEmpty()) {
            onFailure.accept(new ApiError(INVALID_TOKEN));
            return;
        }

        if (newPassword == null || newPassword.trim().isEmpty()) {
            onFailure.accept(new ApiError(User.INVALID_PASSWORD));
            return;
        }

        ApiRequest request = new ApiRequest.Builder()
                .addBody(new PasswordResetRequest(accessToken, newPassword))
                .buildAsBody();

        IServerConnector<PasswordResetResponse> connector = getPasswordResetFrom(getUrl() + "/api/auth/password_reset/confirm/", request);
        if (connector.connect()) {
            PasswordResetResponse response = connector.getResponse().castResponseAs(PasswordResetResponse.class);
            onSuccess.accept(response.getStatus());
        }
        else {
            onFailure.accept(connector.getError());
        }
    }

    @Override
    public void getHistory(String accessToken, User user, Consumer<List<Sale>> onSuccess, Consumer<ApiError> onFailure) {
        if (accessToken == null || accessToken.trim().isEmpty()) {
            onFailure.accept(new ApiError(INVALID_TOKEN));
            return;
        }

        IServerConnector<Sale> connector = getHistoryFrom(getUrl() + "/api/compras/" + user.getId(), accessToken);
        if (connector.connect()) {
            Type listType = new TypeToken<List<Sale>>() {}.getType();
            List<Sale> sales = connector.getResponse().castResponseAsListOf(listType);
            onSuccess.accept(sales);
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

    protected IServerConnector<ResetTokenResponse> getTokenResetFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostRequester<>(request, new ApiResponseWrapper()), this.connectionFactory);
    }

    protected IServerConnector<PasswordResetResponse> getPasswordResetFrom(String url, ApiRequest request) {
        return new ServerConnector<>(url, new PostRequester<>(request, new DjangoApiResetPasswordResponseWrapper()), this.connectionFactory);
    }

    protected IServerConnector<Sale> getHistoryFrom(String url, String accessToken) {
        return new ServerConnector<>(url, new AuthenticatedGetRequester<>(new ApiResponseWrapper(), accessToken), this.connectionFactory);
    }
}