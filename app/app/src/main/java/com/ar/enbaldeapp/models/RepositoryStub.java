package com.ar.enbaldeapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryStub implements IRepository {
    private User loggedUser;
    private final Map<Integer, ProductType> productTypes;
    private final List<Selection> selections;
    private final List<Product> catalog;
    private final List<Sale> sales;
    private final List<ShippingMethod> shippingMethods;

    public RepositoryStub() {
        selections = new ArrayList<>();
        sales = new ArrayList<>();
        productTypes = Map.of(
                1, new ProductType(1, "Balde"),
                2, new ProductType(2, "Palito"),
                3, new ProductType(3, "Tacita"));

        catalog = new ArrayList<>(List.of(
                new Product(1, "Helado de chocolate", "Un helado de chocolate Aguila", 3500, 10, "images/helado-chocolate.png", productTypes.get(1)),
                new Product(2, "Helado de frutilla", "Helado de frutilla con trozos de fruta natural", 5500, 6, "images/helado-frutilla.png", productTypes.get(2)),
                new Product(3, "Helado granizado", "Helado de dulce de leche con chips de chocolate", 1500, 20, "images/helado-granizado.png", productTypes.get(2))
                ));

        shippingMethods = new ArrayList<>(List.of(
                new ShippingMethod(1, "Retiro de local", 0),
                new ShippingMethod(2, "Envío prioritario", 5700),
                new ShippingMethod(3, "Envío normal", 2500)
        ));
    }

    @Override
    public List<Product> getCatalog() {
        return this.catalog;
    }

    @Override
    public boolean resetCart() {
        this.selections.clear();
        return true;
    }

    @Override
    public List<Selection> getCart() {
        return this.selections;
    }

    @Override
    public void removeProductFromCart(Product product) {
        this.selections.removeIf(p -> p.getId() == product.getId());
    }

    @Override
    public boolean addProductToCart(Product product, int quantity) {
        List<Selection> existingSelections = this.selections.stream().filter(p -> p.getId() == product.getId()).collect(Collectors.toList());
        if (existingSelections.isEmpty()) {
            this.selections.add(new Selection(this.selections.size(), product, quantity, new ArrayList<>(), 0, product.getPrice() * quantity));
        }
        else {
            int newQuantity = existingSelections.stream().mapToInt(Selection::getQuantity).sum() + quantity;
            double newTotal = newQuantity * product.getPrice();
            Selection newSelection = new Selection(existingSelections.size(), product, newQuantity, new ArrayList<>(), 0, newTotal);
            this.selections.removeAll(existingSelections);
            this.selections.add(newSelection);
        }

        return true;
    }

    @Override
    public User login(String username, String password) {
        if (this.loggedUser == null) {
            this.loggedUser = new User(1, "Perez", "Juan", "juan.perez@gmail.com", "123 Main St Miami FL", "1234-5678", "Good client", username, password);
        }

        return this.loggedUser;
    }

    @Override
    public void logout() {
        loggedUser = null;
    }

    @Override
    public List<Sale> getSales(User user) {
        return this.sales;
    }

    @Override
    public List<ShippingMethod> getShippingMethods() {
        return this.shippingMethods;
    }
}

