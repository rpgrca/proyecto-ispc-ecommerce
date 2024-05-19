package com.ar.enbaldeapp.support;

import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.models.UserToken;
import com.ar.enbaldeapp.services.ApiRequest;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IApiServices;
import com.ar.enbaldeapp.services.IServerConnector;

import java.util.function.BiFunction;

public class ApiServicesStub extends ApiServices {
    private final BiFunction<String, ApiRequest, IServerConnector<User>> getUserFromCallback;
    private final BiFunction<String, String, IServerConnector<Boolean>> disconnectFromCallback;
    private BiFunction<String, ApiRequest, IServerConnector<UserToken>> getUserTokenFromCallback;

    public static class Builder {
        private BiFunction<String, ApiRequest, IServerConnector<User>> getUserFromCallback;
        private BiFunction<String, String, IServerConnector<Boolean>> disconnectFromCallback;
        private BiFunction<String, ApiRequest, IServerConnector<UserToken>> getUserTokenFromCallback;

        public Builder withGetUserFromCallback(BiFunction<String, ApiRequest, IServerConnector<User>> getUserFromCallback) {
            this.getUserFromCallback = getUserFromCallback;
            return this;
        }

        public Builder withDisconnectFromCallback(BiFunction<String, String, IServerConnector<Boolean>> disconnectFromCallback) {
            this.disconnectFromCallback = disconnectFromCallback;
            return this;
        }

        public IApiServices build() {
            return new ApiServicesStub(this.getUserFromCallback, this.disconnectFromCallback, this.getUserTokenFromCallback);
        }

        public Builder withGetUserTokenFromCallback(BiFunction<String, ApiRequest, IServerConnector<UserToken>> getUsertokenFromCallback) {
            this.getUserTokenFromCallback = getUsertokenFromCallback;
            return this;
        }
    }

    public ApiServicesStub(BiFunction<String, ApiRequest, IServerConnector<User>> getUserFromCallback, BiFunction<String, String, IServerConnector<Boolean>> disconnectFromCallback, BiFunction<String, ApiRequest, IServerConnector<UserToken>> getUserTokenFromCallback) {
        this.getUserFromCallback = getUserFromCallback;
        this.disconnectFromCallback = disconnectFromCallback;
        this.getUserTokenFromCallback = getUserTokenFromCallback;
    }

    @Override
    protected IServerConnector<User> getUserFrom(String url, ApiRequest request) {
        return getUserFromCallback.apply(url, request);
    }

    @Override
    protected IServerConnector<Boolean> disconnectFrom(String url, String accessToken) {
        return disconnectFromCallback.apply(url, accessToken);
    }

    @Override
    protected IServerConnector<UserToken> getUserTokenFrom(String url, ApiRequest request) {
        return getUserTokenFromCallback.apply(url, request);
    }
}