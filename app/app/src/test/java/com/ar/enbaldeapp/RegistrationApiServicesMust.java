package com.ar.enbaldeapp;

import static com.ar.enbaldeapp.support.Constants.ADDRESS;
import static com.ar.enbaldeapp.support.Constants.EMAIL;
import static com.ar.enbaldeapp.support.Constants.FIRST_NAME;
import static com.ar.enbaldeapp.support.Constants.LAST_NAME;
import static com.ar.enbaldeapp.support.Constants.PASSWORD;
import static com.ar.enbaldeapp.support.Constants.PHONE;
import static com.ar.enbaldeapp.support.Constants.USERNAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.ar.enbaldeapp.models.User;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.support.ApiServicesRegistrationSpy;
import com.ar.enbaldeapp.support.ApiServicesRegistrationStub;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(Theories.class)
public class RegistrationApiServicesMust {
    @Test
    public void callErrorCallback_whenConnectFails() {
        AtomicBoolean errorCalled = new AtomicBoolean(false);
        ApiServices sut = new ApiServicesRegistrationStub(false);

        sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, u -> {}, e -> errorCalled.set(true));
        assertTrue(errorCalled.get());
    }

    @Test
    public void callSuccessCallback_whenConnectWorks() {
        AtomicBoolean successCalled = new AtomicBoolean(false);
        ApiServices sut = new ApiServicesRegistrationStub(true);

        sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, u -> successCalled.set(true), e -> {});
        assertTrue(successCalled.get());
    }

    @Test
    public void sendCorrectInformationToServer() {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();

        sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, u -> {}, e -> {});

        MatcherAssert.assertThat(sut.getRequest().getData(), CoreMatchers.containsString(FIRST_NAME));
        MatcherAssert.assertThat(sut.getRequest().getData(), CoreMatchers.containsString(LAST_NAME));
        MatcherAssert.assertThat(sut.getRequest().getData(), CoreMatchers.containsString(EMAIL));
        MatcherAssert.assertThat(sut.getRequest().getData(), CoreMatchers.containsString(ADDRESS));
        MatcherAssert.assertThat(sut.getRequest().getData(), CoreMatchers.containsString(PHONE));
        MatcherAssert.assertThat(sut.getRequest().getData(), CoreMatchers.containsString(USERNAME));
        MatcherAssert.assertThat(sut.getRequest().getData(), CoreMatchers.containsString(PASSWORD));
    }

    @DataPoints("invalid strings")
    public static String[] invalidStrings() {
        return new String[]{"", null, "   "};
    }

    @Theory
    public void doNotContactServer_whenFirstNameIsInvalid(@FromDataPoints("invalid strings") String invalidName) {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        sut.register(invalidName, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, u -> {}, e -> {});

        assertNull(sut.getRequest());
    }

    @Theory
    public void doNotContactServer_whenLastNameIsInvalid(@FromDataPoints("invalid strings") String invalidName) {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        sut.register(FIRST_NAME, invalidName, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, u -> {}, e -> {});

        assertNull(sut.getRequest());
    }

    @Theory
    public void doNotContactServer_whenEmailIsInvalid(@FromDataPoints("invalid strings") String invalidEmail) {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        sut.register(FIRST_NAME, LAST_NAME, invalidEmail, ADDRESS, PHONE, USERNAME, PASSWORD, u -> {}, e -> {});

        assertNull(sut.getRequest());
    }

    @Theory
    public void doNotContactServer_whenAddressIsInvalid(@FromDataPoints("invalid strings") String invalidAddress) {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        sut.register(FIRST_NAME, LAST_NAME, EMAIL, invalidAddress, PHONE, USERNAME, PASSWORD, u -> {}, e -> {});

        assertNull(sut.getRequest());
    }

    @Theory
    public void doNotContactServer_whenUsernameIsInvalid(@FromDataPoints("invalid strings") String invalidUsername) {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, invalidUsername, PASSWORD, u -> {}, e -> {});

        assertNull(sut.getRequest());
    }

    @Theory
    public void doNotContactServer_whenPasswordIsInvalid(@FromDataPoints("invalid strings") String invalidPassword) {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, invalidPassword, u -> {}, e -> {});

        assertNull(sut.getRequest());
    }

    @Test
    public void throwException_whenSuccessCallbackIsInvalid() {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, null, e -> {}));
        assertEquals("El callback por éxito es inválido", exception.getMessage());
    }

    @Test
    public void throwException_whenFailureCallbackIsInvalid() {
        ApiServicesRegistrationSpy sut = new ApiServicesRegistrationSpy();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, u -> {}, null));
        assertEquals("El callback por fallo es inválido", exception.getMessage());
    }

    @Test
    public void deserializeServerReplyCorrectly() {
        AtomicReference<User> atomicUser = new AtomicReference<>();
        ApiServices sut = new ApiServicesRegistrationStub(true);
        sut.register(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, USERNAME, PASSWORD, u -> atomicUser.set(u), e -> {});

        User user = atomicUser.get();
        assertEquals("Juan", user.getFirstName());
        assertEquals("Perez", user.getLastName());
        assertEquals("juan8", user.getUsername());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("11112222", user.getPhone());
        assertEquals("juan.perez@gmail.com", user.getEmail());
        assertEquals("", user.getObservations());
        assertEquals(User.Client, user.getType());
    }
}