package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.ApiRequest;
import com.ar.enbaldeapp.services.connection.IHttpUrlConnectionWrapper;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class PutRequester<T> extends Requester<T> {
    private final ApiRequest request;

    public PutRequester(ApiRequest request) {
        this.request = request;
    }

    @Override
    public void sendRequestTo(IHttpUrlConnectionWrapper connection) throws IOException {
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + request.getAccessToken());

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(this.request.getData());
        out.close();
    }
}
