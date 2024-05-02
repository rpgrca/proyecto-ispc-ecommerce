package com.ar.enbaldeapp.models;

public class ProductType {
    private final long id;
    private final String name;

    public ProductType(long id, String name) {
        if (id <= 0) throw new RuntimeException("El id es inválido");
        if (name == null || name.trim().isEmpty()) throw new RuntimeException("El nombre es inválido");

        this.id = id;
        this.name = name;
    }

    public long getId() { return id; }
    public String getName() { return name; }
}
