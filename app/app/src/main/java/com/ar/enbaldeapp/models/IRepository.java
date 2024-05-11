package com.ar.enbaldeapp.models;

import java.util.List;

public interface IRepository {
    List<Product> getCatalog();
    List<Sale> getSales(User user);
    List<ShippingMethod> getShippingMethods();

    boolean resetCart();
    List<Selection> getCart();
    void removeProductFromCart(Product product);
    boolean addProductToCart(Product product, int quantity);

    User login(String username, String password);
    void logout();
}
