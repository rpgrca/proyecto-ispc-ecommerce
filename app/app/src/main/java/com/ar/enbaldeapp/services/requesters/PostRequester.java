package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.ApiRequest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class PostRequester implements IRequester {
    private final ApiRequest request;

    public PostRequester(ApiRequest request) {
        this.request = request;
    }

    @Override
    public void sendRequestTo(HttpURLConnection connection) throws IOException {
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.request.getBoundary());
        connection.setRequestProperty("Accept", "application/json");

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(this.request.getData());
        out.close();
    }

    @Override
    public String preprocessResponse(String response) {
        return response;
    }
}