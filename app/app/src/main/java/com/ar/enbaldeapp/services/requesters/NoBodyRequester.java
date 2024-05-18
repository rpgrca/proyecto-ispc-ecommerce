package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.IHttpUrlConnectionWrapper;

import java.net.HttpURLConnection;

public class NoBodyRequester implements IRequester {
    @Override
    public void sendRequestTo(IHttpUrlConnectionWrapper connection) {
    }

    @Override
    public String preprocessResponse(String response) {
        return response;
    }
}
