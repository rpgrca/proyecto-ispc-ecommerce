package com.ar.enbaldeapp.services.requesters;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface IRequester {
    void sendRequestTo(HttpURLConnection connection) throws IOException;
    String preprocessResponse(String response);
}
