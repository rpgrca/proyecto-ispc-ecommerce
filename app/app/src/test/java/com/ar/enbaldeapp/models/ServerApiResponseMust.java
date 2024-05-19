package com.ar.enbaldeapp.models;

import static com.ar.enbaldeapp.support.Constants.LOGIN_OK_JSON;

import static org.junit.Assert.assertEquals;

import com.ar.enbaldeapp.models.utilities.JsonUtilities;
import com.ar.enbaldeapp.services.ServerApiResponse;
import com.google.gson.internal.LinkedTreeMap;

import org.junit.Test;

public class ServerApiResponseMust {
    @Test
    public void deserializeCorrectly() {
        ServerApiResponse sut = JsonUtilities.getConfiguredGson().fromJson(LOGIN_OK_JSON, ServerApiResponse.class);
        Object object = sut.getData();
        assertEquals("Inicio de sesión exitoso", sut.getMessage());
        assertEquals(200, sut.getStatus());
        assertEquals(6L, ((LinkedTreeMap<?, ?>) object).get("carritoActual"));
    }
}
