package com.ar.enbaldeapp.support;

import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.services.ApiError;
import com.ar.enbaldeapp.services.ApiRequest;
import com.ar.enbaldeapp.services.ApiResponse;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IServerConnector;

public class ApiServicesRegistrationSpy extends ApiServices {
    private ApiRequest request;

    private static class ServerConnectorRegistrationDummy implements IServerConnector<User> {
        @Override
        public boolean connect() {
            return false;
        }

        @Override
        public ApiResponse<User> getResponse() {
            return null;
        }

        @Override
        public ApiError getError() {
            return new ApiError("Error conectando al sitio");
        }
    }

    @Override
    protected IServerConnector<User> getUserFrom(String url, ApiRequest request) {
        this.request = request;
        return new ServerConnectorRegistrationDummy();
    }

    public ApiRequest getRequest() {
        return this.request;
    }
}