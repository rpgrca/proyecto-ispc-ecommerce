package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.IHttpUrlConnectionWrapper;
import com.ar.enbaldeapp.services.wrappers.IResponseWrapper;

import java.io.IOException;
import java.net.HttpURLConnection;

public class GetRequester implements IRequester {
    private final IResponseWrapper wrapper;

    public GetRequester(IResponseWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void sendRequestTo(IHttpUrlConnectionWrapper connection) throws IOException {
        connection.setRequestMethod("GET");
    }

    @Override
    public String preprocessResponse(String response) {
        return wrapper.preprocessResponse(response);
    }
}