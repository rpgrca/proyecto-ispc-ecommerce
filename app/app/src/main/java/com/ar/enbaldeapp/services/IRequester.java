package com.ar.enbaldeapp.services;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface IRequester {
    void sendRequestTo(HttpURLConnection connection) throws IOException;
    String getBoundary();
}
