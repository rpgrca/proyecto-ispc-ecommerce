package com.ar.enbaldeapp.services.reply;

import com.ar.enbaldeapp.services.ApiError;
import com.ar.enbaldeapp.services.ApiResponse;
import com.ar.enbaldeapp.services.requesters.IRequester;

import java.io.IOException;
import java.io.InputStream;

public class OkServerReply<T> extends ServerReply<T> {
    private final InputStream inputStream;
    private final IRequester<T> requester;

    public OkServerReply(InputStream inputStream, IRequester<T> requester) {
        this.inputStream = inputStream;
        this.requester = requester;
    }

    @Override
    public ApiResponse<T> getResponse() throws IOException {
        String jsonText = this.loadInputFrom(this.inputStream);
        jsonText = this.requester.preprocessResponse(jsonText);

        return new ApiResponse<T>(jsonText, true);
    }

    @Override
    public ApiError getError() {
        return null;
    }

    @Override
    public boolean getReturnValue() {
        return true;
    }
}
