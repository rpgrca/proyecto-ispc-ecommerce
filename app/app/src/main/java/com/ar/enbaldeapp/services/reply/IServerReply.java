package com.ar.enbaldeapp.services.reply;

import com.ar.enbaldeapp.services.ApiError;
import com.ar.enbaldeapp.services.ApiResponse;
import com.ar.enbaldeapp.services.IResponseCreator;

import java.io.IOException;

public interface IServerReply<T> {
    ApiResponse<T> getResponse(IResponseCreator responseCreator) throws IOException;
    ApiError getError() throws IOException;
    boolean getReturnValue();
}
