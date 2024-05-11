/*
package com.ar.enbaldeapp;

import static com.ar.enbaldeapp.support.Constants.ADDRESS;
import static com.ar.enbaldeapp.support.Constants.EMAIL;
import static com.ar.enbaldeapp.support.Constants.FIRST_NAME;
import static com.ar.enbaldeapp.support.Constants.LAST_NAME;
import static com.ar.enbaldeapp.support.Constants.PASSWORD;
import static com.ar.enbaldeapp.support.Constants.PHONE;
import static com.ar.enbaldeapp.support.Constants.USERNAME;
import static org.junit.Assert.assertTrue;

import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.support.ApiServicesLoginStub;
import com.ar.enbaldeapp.support.ApiServicesRegistrationStub;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(Theories.class)
public class LoginApiServicesMust {
    @Test
    public void callErrorCallback_whenConnectFails() {
        AtomicBoolean errorCalled = new AtomicBoolean(false);
        ApiServices sut = new ApiServicesLoginStub(false);

        sut.login(USERNAME, PASSWORD, u -> {
        }, e -> errorCalled.set(true));
        assertTrue(errorCalled.get());
    }

    @Test
    public void callSuccessCallback_whenConnectWorks() {
        AtomicBoolean successCalled = new AtomicBoolean(false);
        ApiServices sut = new ApiServicesLoginStub(true);

        sut.login(USERNAME, PASSWORD, u -> successCalled.set(true), e -> {});
        assertTrue(successCalled.get());
    }
}
*/