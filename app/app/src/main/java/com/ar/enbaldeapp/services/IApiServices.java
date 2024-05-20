package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.Product;
import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.UserToken;

import java.util.List;
import java.util.function.Consumer;

public interface IApiServices {
    void login(String username, String password, Consumer<UserToken> onSuccess, Consumer<ApiError> onError);
    void logout(String accessToken, Consumer<String> onSuccess, Consumer<ApiError> onError);
    void register(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password, Consumer<User> onSuccess, Consumer<ApiError> onError);
    void getCatalogue(Consumer<List<Product>> onSuccess, Consumer<ApiError> onError);
}
