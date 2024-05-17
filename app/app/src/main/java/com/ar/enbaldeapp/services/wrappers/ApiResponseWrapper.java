package com.ar.enbaldeapp.services.wrappers;

public class ApiResponseWrapper implements IResponseWrapper {
    @Override
    public String preprocessResponse(String response) {
        return "{ \"status\": 200, \"mensaje\": \"Catalogo enviado exitosamente.\", \"data\": " + response + " }";
    }
}
