package com.ar.enbaldeapp.services;

import java.net.HttpURLConnection;

public class NoBody implements IRequester {
    @Override
    public void sendRequestTo(HttpURLConnection connection) {
    }

    @Override
    public String getBoundary() {
        return "";
    }
}
