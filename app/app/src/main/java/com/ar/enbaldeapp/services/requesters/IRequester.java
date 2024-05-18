package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.IHttpUrlConnectionWrapper;
import com.ar.enbaldeapp.services.IServerReply;

import java.io.IOException;

public interface IRequester<T> {
    void sendRequestTo(IHttpUrlConnectionWrapper connection) throws IOException;
    String preprocessResponse(String response);
    IServerReply<T> getReplyFromServer(IHttpUrlConnectionWrapper connection) throws IOException;
}
