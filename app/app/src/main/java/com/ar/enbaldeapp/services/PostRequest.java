package com.ar.enbaldeapp.services;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class PostRequest implements IRequester {
    private final ApiRequest request;

    public PostRequest(ApiRequest request) {
        this.request = request;
    }

    @Override
    public void sendRequestTo(HttpURLConnection connection) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(this.request.getData());
        out.close();
    }

    @Override
    public String getBoundary() {
        return this.request.getBoundary();
    }
}