package com.fawry.model;

import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private LocalDate expiryDate;
    private Double weight;

    public Product(String name, double price, int quantity, LocalDate expiryDate, Double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public Double getWeight() { return weight; }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    public boolean isShippable() {
        return weight != null;
    }

    public Shippable getShippableItem() {
        if (!isShippable()) {
            throw new IllegalStateException("Product is not shippable");
        }
        return new Shippable() {
            @Override
            public String getName() {
                return Product.this.name;
            }
            @Override
            public double getWeight() {
                return Product.this.weight;
            }
        };
    }
}