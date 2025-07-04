package com.fawry.model;

import java.util.*;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }
        items.merge(product, quantity, Integer::sum);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public double getSubtotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public boolean hasShippable() {
        return items.keySet().stream().anyMatch(Product::isShippable);
    }
}