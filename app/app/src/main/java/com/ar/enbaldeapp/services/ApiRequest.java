package com.ar.enbaldeapp.services;

import java.util.Random;

public class ApiRequest {
    public static class Builder {
        private final static char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        private final StringBuilder stringBuilder;
        private final String boundary;

        public Builder() {
            stringBuilder = new StringBuilder();
            boundary = generateBoundary(); // "-----------------------------316364238710708646983401506042";
        }

        private String generateBoundary() {
            StringBuilder buffer = new StringBuilder();
            Random rand = new Random();
            int count = rand.nextInt(11) + 30; // a random size from 30 to 40
            buffer.append("-".repeat(59 - count));
            for (int i = 0; i < count; i++) {
                buffer.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
            }

            return buffer.toString();
        }

        public Builder addContentDisposition(String key, String value) {
            addSeparator();
            stringBuilder.append("Content-Disposition: form-data; name=" + "\"" + key + "\"\r\n\r\n");
            stringBuilder.append(value + "\r\n");
            return this;
        }

        private void addSeparator() {
            stringBuilder.append("--" + this.boundary + "\r\n");
        }

        private void addFinalSeparator() {
            stringBuilder.append("--" + this.boundary + "--" + "\r\n");
        }

        private String popContentData() {
            String result = stringBuilder.toString();
            stringBuilder.setLength(0);
            return result;
        }

        public ApiRequest Build() {
            addFinalSeparator();
            String result = popContentData();
            return new ApiRequest(result, boundary);
        }
    }

    private final String data;
    private final String boundary;

    private ApiRequest(String data, String boundary) {
        this.data = data;
        this.boundary = boundary;
    }

    public String getData() {
        return this.data;
    }

    public String getBoundary() { return this.boundary; }
}
