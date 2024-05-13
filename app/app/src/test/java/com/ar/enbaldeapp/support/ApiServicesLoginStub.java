package com.ar.enbaldeapp.support;

import static com.ar.enbaldeapp.support.Constants.REGISTRATION_OK_JSON;

import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.services.ApiError;
import com.ar.enbaldeapp.services.ApiRequest;
import com.ar.enbaldeapp.services.ApiResponse;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.services.IServerConnector;

public class ApiServicesLoginStub extends ApiServices {
    private final boolean connectReturnValue;

    public ApiServicesLoginStub(boolean connectReturnValue) {
        this.connectReturnValue = connectReturnValue;
    }

    private static class ServerConnectorLoginStub implements IServerConnector<User> {
        private final boolean connectReturnValue;

        public ServerConnectorLoginStub(boolean connectReturnValue) {
            this.connectReturnValue = connectReturnValue;
        }

        @Override
        public boolean connect() {
            return connectReturnValue;
        }

        @Override
        public ApiResponse<User> getResponse() {
            return new ApiResponse<>(REGISTRATION_OK_JSON, true);
        }

        @Override
        public ApiError getError() {
            return new ApiError("Error conectando al sitio");
        }
    }

    @Override
    protected IServerConnector<User> getUserFrom(String url, ApiRequest request) {
        return new ServerConnectorLoginStub(this.connectReturnValue);
    }
}
