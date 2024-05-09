package com.ar.enbaldeapp.services;

import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerConnector<T> {
    private final ApiRequest request;
    private URL url;
    private ApiResponse<T> response;
    private ApiError error;

    public ServerConnector(String urlString, ApiRequest request) {
        if (!StringToUrl(urlString)) {
            throw new RuntimeException("Invalid url " + urlString);
        }
        this.request = request;
    }

    public boolean connect() {
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        AtomicBoolean result = new AtomicBoolean(false);
        executor.execute(() -> {
            result.set(load());
        });

        return result.get();
    }

    private boolean load() {
        String jsonText = "";

        try {
            InputStream inputStream = url.openStream();
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                inputStream.close();
                jsonText = stringBuilder.toString();

                this.response = new ApiResponse(jsonText);
                return true;
            }
        } catch (IOException ex) {
            error = new ApiError(ex.getMessage());
        }

        return false;
    }

    private boolean StringToUrl(String urlString) {
        try {
            url = new URL(urlString);
            return true;
        } catch (MalformedURLException ex) {
            error = new ApiError(ex.getMessage());
        }

        return false;
    }
}
