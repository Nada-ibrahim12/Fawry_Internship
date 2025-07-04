package com.fawry.service;

import com.fawry.model.*;
import java.util.*;

public class CheckoutService {
    private ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Map<Product, Integer> items = cart.getItems();
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            if (product.getQuantity() < quantity) {
                throw new IllegalStateException("Product " + product.getName() + " is out of stock");
            }
            
            if (product.isExpired()) {
                throw new IllegalStateException("Product " + product.getName() + " is expired");
            }
        }

        double subtotal = cart.getSubtotal();
        double shippingFee = cart.hasShippable() ? 30 : 0;
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient customer balance");
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() - quantity);
        }

        customer.deduct(total);

        List<Shippable> shippableItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            if (product.isShippable()) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add(product.getShippableItem());
                }
            }
        }
        
        shippingService.shipItems(shippableItems);
        
    
        printReceipt(items, subtotal, shippingFee, total, customer.getBalance());
    }

    private void printReceipt(Map<Product, Integer> items, double subtotal, 
                              double shippingFee, double total, double balance) {
        System.out.println("** Checkout receipt **");
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double itemTotal = product.getPrice() * quantity;
            
            System.out.printf("%dx %s %.0f%n", quantity, product.getName(), itemTotal);
        }
        
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFee);
        System.out.printf("Amount %.0f%n", total);
        System.out.printf("Customer current balance: %.0f%n", balance);
    }
}