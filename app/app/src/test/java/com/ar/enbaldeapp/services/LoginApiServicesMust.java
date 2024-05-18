package com.ar.enbaldeapp.services;

import static com.ar.enbaldeapp.support.Constants.PASSWORD;
import static com.ar.enbaldeapp.support.Constants.USERNAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.ar.enbaldeapp.support.ApiServicesLoginStub;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(Theories.class)
public class LoginApiServicesMust {
    @Test
    public void throwException_whenSuccessCallbackIsInvalid() {
        ApiServices sut = new ApiServices();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.login(USERNAME, PASSWORD, null, e -> {}));
        assertEquals("El callback por éxito es inválido", exception.getMessage());
    }

    @Test
    public void throwException_whenFailureCallbackIsInvalid() {
        ApiServices sut = new ApiServices();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.login(USERNAME, PASSWORD, u -> {}, null));
        assertEquals("El callback por fallo es inválido", exception.getMessage());
    }

    @Test
    public void callErrorCallback_whenConnectFails() {
        AtomicBoolean errorCalled = new AtomicBoolean(false);
        ApiServices sut = new ApiServicesLoginStub(false);

        sut.login(USERNAME, PASSWORD, u -> {}, e -> errorCalled.set(true));
        assertTrue(errorCalled.get());
    }
/*
    @Test
    public void callSuccessCallback_whenConnectWorks() {
        AtomicBoolean successCalled = new AtomicBoolean(false);
        ApiServices sut = new ApiServicesLoginStub(true);

        sut.login(USERNAME, PASSWORD, u -> successCalled.set(true), e -> {});
        assertTrue(successCalled.get());
    }*/
}
