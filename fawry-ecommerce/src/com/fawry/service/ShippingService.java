package com.fawry.service;

import com.fawry.model.Shippable;
import java.util.*;

public class ShippingService {
    public void shipItems(List<Shippable> items) {
        if (items.isEmpty()) return;

        Map<String, Map<Double, Long>> groupedItems = new HashMap<>();
        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            Map<Double, Long> weightMap = groupedItems.computeIfAbsent(name, k -> new HashMap<>());
            weightMap.put(weight, weightMap.getOrDefault(weight, 0L) + 1);
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        for (Map.Entry<String, Map<Double, Long>> nameEntry : groupedItems.entrySet()) {
            String name = nameEntry.getKey();
            for (Map.Entry<Double, Long> weightEntry : nameEntry.getValue().entrySet()) {
                double weight = weightEntry.getKey();
                long quantity = weightEntry.getValue();
                System.out.printf("%dx %s %.0fg%n", quantity, name, weight * 1000);
                totalWeight += weight * quantity;
            }
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}