package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.ErrorServerReply;
import com.ar.enbaldeapp.services.IHttpUrlConnectionWrapper;
import com.ar.enbaldeapp.services.IServerReply;
import com.ar.enbaldeapp.services.OkServerReply;

import java.io.IOException;
import java.net.HttpURLConnection;

public abstract class Requester<T> implements IRequester<T> {
    @Override
    public IServerReply<T> getReplyFromServer(IHttpUrlConnectionWrapper connection) throws IOException {
        IServerReply<T> serverReply;

        if (connection.getResponseCode() >= HttpURLConnection.HTTP_OK && connection.getResponseCode() <= 299) {
            serverReply = new OkServerReply<T>(connection.getInputStream(), this);
        } else {
            serverReply = new ErrorServerReply<T>(connection.getErrorStream(), this);
        }

        return serverReply;
    }
}
