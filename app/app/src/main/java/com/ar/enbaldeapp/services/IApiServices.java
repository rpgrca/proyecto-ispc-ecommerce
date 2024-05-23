package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.Cart;
import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.Selection;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.UserToken;

import java.util.List;
import java.util.function.Consumer;

public interface IApiServices {
    String getUrl();
    void login(String username, String password, Consumer<UserToken> onSuccess, Consumer<ApiError> onFailure);
    void logout(String accessToken, Consumer<String> onSuccess, Consumer<ApiError> onFailure);
    void register(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onFailure);
    void getCatalogue(Consumer<List<Product>> onSuccess, Consumer<ApiError> onFailure);
    void getCart(String accessToken, long cartId, Consumer<Cart> onSuccess, Consumer<ApiError> onFailure);
    void addToCart(String accessToken, Cart cart, Product product, int amount, Consumer<Selection> onSuccess, Consumer<ApiError> onFailure);
    void sendRecoveryToken(String email, Consumer<String> onSuccess, Consumer<ApiError> onFailure);
    void resetPassword(String token, String newPassword, Consumer<String> onSuccess, Consumer<ApiError> onFailure);
}
