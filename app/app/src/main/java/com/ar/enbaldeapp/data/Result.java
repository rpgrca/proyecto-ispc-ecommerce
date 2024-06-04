package com.ar.enbaldeapp.data;

import androidx.annotation.NonNull;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @NonNull
    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success<T> success = (Result.Success<T>) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error<T> error = (Result.Error<T>) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends Result<T> {
        private final T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error<T> extends Result<T> {
        private final Exception error;

        private final String message;

        public Error(Exception error) {
            this.error = error;
            this.message = null;
        }

        public Error(String message) {
            this.message = message;
            this.error = null;
        }

        public Exception getError() {
            return this.error;
        }

        public String getMessage() {
            return this.message;
        }

    }
}