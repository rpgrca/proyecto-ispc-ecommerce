package com.ar.enbaldeapp.services;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerConnector<T> implements IServerConnector<T> {
    private URL url;
    private ApiResponse<T> response;
    private ApiError error;
    private final IRequester requester;

    public ServerConnector(String urlString, IRequester requester) {
        if (!StringToUrl(urlString)) {
            throw new RuntimeException("Invalid url " + urlString);
        }
        this.requester = requester;
    }

    @Override
    public boolean connect() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        AtomicBoolean result = new AtomicBoolean(false);
        executor.execute(() -> {
            result.set(load());
        });

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException ex) {
            return false;
        }

        return result.get();
    }

    @Override
    public ApiResponse<T> getResponse() { return this.response; }

    @Override
    public ApiError getError() { return this.error; }

    private boolean load() {
        String jsonText = "";
        boolean isError = false;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + requester.getBoundary());
            connection.setRequestProperty("Accept", "application/json");

            this.requester.sendRequestTo(connection);

            InputStream inputStream;
            if (connection.getResponseCode() >= HttpURLConnection.HTTP_OK && connection.getResponseCode() <= 299) {
                inputStream = connection.getInputStream();
                isError = false;
            }
            else {
                inputStream = connection.getErrorStream();
                isError = true;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            inputStream.close();
            jsonText = stringBuilder.toString();

            if (isError) {
                this.error = new ApiError(JsonParser.parseString(jsonText).getAsJsonObject());
            }
            else {
                this.response = new ApiResponse(jsonText, true);
                return true;
            }
        } catch (IOException ex) {
            error = new ApiError(ex.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
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
