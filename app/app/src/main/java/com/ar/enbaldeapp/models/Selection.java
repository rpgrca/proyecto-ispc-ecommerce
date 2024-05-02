package com.ar.enbaldeapp.models;

public class Selection {
    private final long id;
    private final Product product;
    private final int quantity;
    private final double discount;
    private final double total;

    public Selection(long id, Product product, int quantity, double discount, double total) {
        if (id <= 0) throw new RuntimeException("El id es inválido");
        if (product == null) throw new RuntimeException("El producto es inválido");
        if (quantity <= 0) throw new RuntimeException("La cantidad es inválida");
        if (discount < 0) throw new RuntimeException("El descuento es inválido");
        if (total < 0) throw new RuntimeException("El total es inválido");

        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.total = total;
    }

    public long getId() { return id; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getDiscount() { return discount; }
    public double getTotal() { return total; }
}
