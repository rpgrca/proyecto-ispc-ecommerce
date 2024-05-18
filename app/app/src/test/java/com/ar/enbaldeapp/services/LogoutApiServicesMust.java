package com.ar.enbaldeapp.services;

import static com.ar.enbaldeapp.support.Constants.ACCESS_STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theory;

public class LogoutApiServicesMust {
    @DataPoints("invalid strings")
    public static String[] invalidStrings() {
        return new String[]{"", null, "   "};
    }

    @Theory
    public void throwException_whenAccessStringIsInvalid(@FromDataPoints("invalid strings") String invalidToken) {
        ApiServices sut = new ApiServices();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.logout(invalidToken, s -> {}, e -> {}));
        assertEquals("El access token es inválido", exception.getMessage());
    }

    @Test
    public void throwException_whenSuccessCallbackIsInvalid() {
        ApiServices sut = new ApiServices();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.logout(ACCESS_STRING, null, e -> {}));
        assertEquals("El callback por éxito es inválido", exception.getMessage());
    }

    @Test
    public void throwException_whenFailureCallbackIsInvalid() {
        ApiServices sut = new ApiServices();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.logout(ACCESS_STRING, u -> {}, null));
        assertEquals("El callback por fallo es inválido", exception.getMessage());
    }
}
