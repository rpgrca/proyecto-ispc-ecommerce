package com.ar.enbaldeapp.models;

import java.util.Date;

public class Offer {
    private final long id;
    private final String name;
    private final double discount;
    private final Date expirationDate;

    public Offer(long id, String name, double discount, Date expirationDate) {
        if (id <= 0) throw new RuntimeException("El id es inválido");
        if (name == null || name.trim().isEmpty())
            throw new RuntimeException("El nombre es inválido");
        if (discount < 0) throw new RuntimeException("El descuento es inválido");

        this.id = id;
        this.name = name;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
