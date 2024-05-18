package com.ar.enbaldeapp.services.requesters;

import com.ar.enbaldeapp.services.IHttpUrlConnectionWrapper;

import java.io.IOException;

public interface IRequester {
    void sendRequestTo(IHttpUrlConnectionWrapper connection) throws IOException;
    String preprocessResponse(String response);
}
