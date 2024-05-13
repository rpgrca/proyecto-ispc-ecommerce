package com.ar.enbaldeapp.models;

import java.util.Date;
import java.util.List;

public class Sale {
    private final long id;
    private final String client;
    private final Date date;
    private final List<Selection> selections;
    private final double total;
    private final String shipping;
    private final double shippingCost;
    private final PaymentType paymentType;
    private final String transaction;

    public Sale(long id, User user, Date date, List<Selection> selections, double total, ShippingMethod shipping, PaymentType paymentType, String transaction) {
        if (id <= 0) throw new RuntimeException("El id es inválido");
        if (user == null) throw new RuntimeException("El usuario es inválido");
        if (selections == null) throw new RuntimeException("Las selecciones son inválidas");
        if (total < 0) throw new RuntimeException("El total es inválido");
        if (shipping == null) throw new RuntimeException("El envío es inválido");
        if (transaction == null) throw new RuntimeException("La transacción es inválida");

        this.id = id;
        this.client = user.getFirstName() + " " + user.getLastName();
        this.date = date;
        this.selections = selections;
        this.total = total;
        this.shipping = shipping.getName();
        this.shippingCost = shipping.getCost();
        this.paymentType = paymentType;
        this.transaction = transaction;
    }

    public long getId() { return id; }
    public String getClient() { return client; }
    public Date getDate() { return date; }
    public List<Selection> getSelections() { return selections; }
    public double getTotal() { return total; }
    public String getShippingMethod() { return shipping; }
    public double getShippingCost() { return shippingCost; }
    public PaymentType getPaymentType() { return paymentType; }
    public String getTransaction() { return transaction; }
}
