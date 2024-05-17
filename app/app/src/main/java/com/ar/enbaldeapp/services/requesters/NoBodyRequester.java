package com.ar.enbaldeapp.services.requesters;

import java.net.HttpURLConnection;

public class NoBodyRequester implements IRequester {
    @Override
    public void sendRequestTo(HttpURLConnection connection) {
    }

    @Override
    public String preprocessResponse(String response) {
        return response;
    }
}
