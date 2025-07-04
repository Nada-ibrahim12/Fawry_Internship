package com.fawry;

import com.fawry.model.*;
import com.fawry.service.CheckoutService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
       
        Product cheese = new Product("Cheese", 100, 10, LocalDate.now().plusDays(10), 0.4);
        Product biscuits = new Product("Biscuits", 150, 5, LocalDate.now().plusDays(20), 0.7);
        Product tv = new Product("TV", 1000, 5, null, 10.0);
        Product scratchCard = new Product("Scratch Card", 50, 100, null, null);

        // Test case 1
        System.out.println("\n===== Test Case 1 =====");
        Cart cart1 = new Cart();
        cart1.add(cheese, 2);
        cart1.add(biscuits, 1);

        Customer customer1 = new Customer("Alice", 400);
        new CheckoutService().checkout(customer1, cart1);

        // Test case 2
        System.out.println("\n===== Test Case 2 =====");
        Cart cart2 = new Cart();
        cart2.add(tv, 1);
        cart2.add(cheese, 1);
        cart2.add(scratchCard, 3);

        Customer customer2 = new Customer("Bob", 2000);
        new CheckoutService().checkout(customer2, cart2);

        // Test case 3
        System.out.println("\n===== Test Case 3 (Edge Cases) =====");
        try {
            Cart emptyCart = new Cart();
            new CheckoutService().checkout(new Customer("Charlie", 100), emptyCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}