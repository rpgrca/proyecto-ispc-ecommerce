package com.ar.enbaldeapp;

import static com.ar.enbaldeapp.support.Constants.*;

import static org.junit.Assert.*;

import com.ar.enbaldeapp.models.User;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class UserMust {
    @Test
    public void beCreatedCorrectly_whenInformationIsCorrect() {
        User sut = new User(USER_ID, LAST_NAME, FIRST_NAME, EMAIL, ADDRESS, PHONE, OBSERVATIONS);

        assertEquals(USER_ID, sut.getId());
        assertEquals(LAST_NAME, sut.getLastName());
        assertEquals(FIRST_NAME, sut.getFirstName());
        assertEquals(EMAIL, sut.getEmail());
        assertEquals(ADDRESS, sut.getAddress());
        assertEquals(PHONE, sut.getPhone());
        assertEquals(OBSERVATIONS, sut.getObservations());
    }

    @DataPoints("invalid strings")
    public static String[] invalidStrings() {
        return new String[]{"", null, "   "};
    }

    @DataPoints("invalid emails")
    public static String[] invalidEmails() {
        return new String[] { "", null, "   "/*, "hola", "hola@", "@chat", "hola@gmail", "@gmail.com", "test @gmail.com"*/ };
    }

    @DataPoints("invalid ids")
    public static long[] invalidIds() {
        return new long[] { -1, 0 };
    }

    @Theory
    public void throwException_whenIdIsInvalid(@FromDataPoints("invalid ids") long invalidId) {
        Exception exception = assertThrows(RuntimeException.class, () -> new User(invalidId, LAST_NAME, FIRST_NAME, EMAIL, ADDRESS, PHONE, OBSERVATIONS));
        assertEquals("El id es inválido", exception.getMessage());
    }

    @Theory
    public void throwException_whenLastNameIsInvalid(@FromDataPoints("invalid strings") String invalidLastName) {
        Exception exception = assertThrows(RuntimeException.class, () -> new User(USER_ID, invalidLastName, FIRST_NAME, EMAIL, ADDRESS, PHONE, OBSERVATIONS));
        assertEquals("El apellido es inválido", exception.getMessage());
    }

    @Theory
    public void throwException_whenFirstNameIsInvalid(@FromDataPoints("invalid strings") String invalidFirstName) {
        Exception exception = assertThrows(RuntimeException.class, () -> new User(USER_ID, LAST_NAME, invalidFirstName, EMAIL, ADDRESS, PHONE, OBSERVATIONS));
        assertEquals("El nombre es inválido", exception.getMessage());
    }

    @Theory
    public void throwException_whenEmailIsInvalid(@FromDataPoints("invalid strings") String invalidEmail) {
        Exception exception = assertThrows(RuntimeException.class, () -> new User(USER_ID, LAST_NAME, FIRST_NAME, invalidEmail, ADDRESS, PHONE, OBSERVATIONS));
        assertEquals("El e-mail es inválido", exception.getMessage());
    }

    @Theory
    public void throwException_whenAddressIsInvalid(@FromDataPoints("invalid strings") String invalidAddress) {
        Exception exception = assertThrows(RuntimeException.class, () -> new User(USER_ID, LAST_NAME, FIRST_NAME, EMAIL, invalidAddress, PHONE, OBSERVATIONS));
        assertEquals("La dirección es inválida", exception.getMessage());
    }

    @Test
    public void throwException_whenPhoneIsInvalid() {
        Exception exception = assertThrows(RuntimeException.class, () -> new User(USER_ID, LAST_NAME, FIRST_NAME, EMAIL, ADDRESS, null, OBSERVATIONS));
        assertEquals("El teléfono es inválido", exception.getMessage());
    }

    @Test
    public void throwException_whenObervationsAreInvalid() {
        Exception exception = assertThrows(RuntimeException.class, () -> new User(USER_ID, LAST_NAME, FIRST_NAME, EMAIL, ADDRESS, PHONE, null));
        assertEquals("Las observaciones son inválidas", exception.getMessage());
    }
}

