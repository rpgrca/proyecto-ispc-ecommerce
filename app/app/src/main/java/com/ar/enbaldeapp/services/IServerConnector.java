package com.ar.enbaldeapp.services;

public interface IServerConnector<T> {
    boolean connect();
    ApiResponse<T> getResponse();
    ApiError getError();
}