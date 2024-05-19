package com.ar.enbaldeapp.services;

import com.ar.enbaldeapp.models.Product;

public class CartModificationRequest {
    private final Product product;
    private final int quantity;

    public CartModificationRequest(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
