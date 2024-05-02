package com.ar.enbaldeapp.models;

public class Product {
    private final long id;
    private final String name;
    private final String description;
    private final double price;
    private final int quantity;
    private final String image;
    private final ProductType type;

    public Product(long id, String name, String description, double price, int quantity, String image, ProductType type) {
        if (id <= 0) throw new RuntimeException("El id es inválido");
        if (name == null || name.trim().isEmpty()) throw new RuntimeException("El nombre es inválido");
        if (description == null || description.trim().isEmpty()) throw new RuntimeException("La descripción es inválida");
        if (price <= 0) throw new RuntimeException("El precio es inválido");
        if (quantity < 0) throw new RuntimeException("La cantidad es inválida");
        if (image == null || image.trim().isEmpty()) throw new RuntimeException("La imagen es inválida");
        if (type == null) throw new RuntimeException("El tipo es inválido");

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.type = type;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getImage() { return image; }
    public ProductType getType() { return type; }
}
