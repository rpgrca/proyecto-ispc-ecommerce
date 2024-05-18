package com.ar.enbaldeapp.services;

import java.io.IOException;

public interface IServerReply<T> {
    ApiResponse<T> getResponse() throws IOException;
    ApiError getError() throws IOException;
    boolean getReturnValue();
}
