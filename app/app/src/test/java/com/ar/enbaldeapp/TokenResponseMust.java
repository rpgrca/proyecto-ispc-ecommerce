package com.ar.enbaldeapp;

import static com.ar.enbaldeapp.support.Constants.ACCESS_STRING;
import static com.ar.enbaldeapp.support.Constants.REFRESH_STRING;
import static org.junit.Assert.assertEquals;

import com.ar.enbaldeapp.models.TokenResponse;

import org.junit.Test;

public class TokenResponseMust {
    @Test
    public void setAccessCorrectly() {
        TokenResponse sut = new TokenResponse();
        sut.setAccess(ACCESS_STRING);
        assertEquals(ACCESS_STRING, sut.getAccess());
    }

    @Test
    public void set() {
        TokenResponse sut = new TokenResponse();
        sut.setRefresh(REFRESH_STRING);
        assertEquals(REFRESH_STRING, sut.getRefresh());
    }
}
