package com.ar.enbaldeapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CatalogueApiServicesMust {
    @Test
    public void throwException_whenSuccessCallbackIsInvalid() {
        ApiServices sut = new ApiServices();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.getCatalogue(null, e -> {}));
        assertEquals("El callback por éxito es inválido", exception.getMessage());
    }

    @Test
    public void throwException_whenFailureCallbackIsInvalid() {
        ApiServices sut = new ApiServices();
        Exception exception = assertThrows(RuntimeException.class, () -> sut.getCatalogue(u -> {}, null));
        assertEquals("El callback por fallo es inválido", exception.getMessage());
    }
}
