package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.reply.ErrorServerReply;
import com.ar.enbaldeapp.services.connection.IHttpUrlConnectionWrapper;
import com.ar.enbaldeapp.services.reply.IServerReply;
import com.ar.enbaldeapp.services.reply.OkServerReply;

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
