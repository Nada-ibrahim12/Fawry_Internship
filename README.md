# Fawry E-Commerce System

## Overview

A Java implementation of an e-commerce system with product management, shopping cart functionality, and checkout processing. The system handles both physical (shippable) and digital (non-shippable) products with proper inventory management.

## Features

- **Product Management**:
  - Track product inventory, prices, and expiry dates
  - Handle both shippable (physical) and non-shippable (digital) products
  - Automatic expiry date checking

- **Shopping Cart**:
  - Add/remove products with quantity validation
  - Calculate subtotals
  - Check for shippable items

- **Checkout System**:
  - Process payments with balance validation
  - Calculate shipping fees
  - Generate receipts
  - Handle inventory updates
  - Create shipping notices

## Class Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── fawry/
│               ├── model/
│               │   ├── Cart.java
│               │   ├── Customer.java
│               │   ├── Product.java
│               │   └── Shippable.java
│               ├── service/
│               │   ├── CheckoutService.java
│               │   └── ShippingService.java
│               └── Main.java
```

## Key Components

### Model Classes

1. **Product**:
   - Name, price, quantity
   - Expiry date (for perishable items)
   - Weight (for shippable items)
   - Methods to check if product is shippable or expired

2. **Cart**:
   - Stores products with quantities
   - Validates stock availability
   - Calculates subtotals
   - Checks for shippable items

3. **Customer**:
   - Name and balance
   - Handles payment deductions

### Service Classes

1. **CheckoutService**:
   - Validates cart contents
   - Processes payments
   - Updates inventory
   - Coordinates shipping
   - Generates receipts

2. **ShippingService**:
   - Groups similar items for shipping
   - Calculates total package weight
   - Generates shipping notices

## Usage Examples

### Running the Application

```java
public class Main {
    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 100, 10, LocalDate.now().plusDays(10), 0.4);
        Product biscuits = new Product("Biscuits", 150, 5, LocalDate.now().plusDays(20), 0.7);
        Product tv = new Product("TV", 1000, 5, null, 10.0);
        Product scratchCard = new Product("Scratch Card", 50, 100, null, null);

        // Test case 1
        Cart cart1 = new Cart();
        cart1.add(cheese, 2);
        cart1.add(biscuits, 1);
        new CheckoutService().checkout(new Customer("Alice", 400), cart1);

        // Test case 2
        Cart cart2 = new Cart();
        cart2.add(tv, 1);
        cart2.add(cheese, 1);
        cart2.add(scratchCard, 3);
        new CheckoutService().checkout(new Customer("Bob", 2000), cart2);
    }
}
```

### Sample Output

```
** Checkout receipt **
2x Cheese 200
1x Biscuits 150
----------------------
Subtotal 350
Shipping 30
Amount 380
Customer current balance: 20

** Shipment notice **
2x Cheese 400g
1x Biscuits 700g
Total package weight 1.1kg
```

## Design Principles

1. **Separation of Concerns**:
   - Clear division between models and services
   - Single responsibility for each class

2. **Encapsulation**:
   - Private fields with controlled access
   - Immutable views of collections

3. **Validation**:
   - Stock availability checks
   - Customer balance validation
   - Expiry date checking

4. **Extensibility**:
   - Interface-based design for shipping
   - Easy to add new product types

## Getting Started

### Prerequisites

- Java JDK 11+
- Maven or Gradle

### Building and Running

1. Clone the repository
2. Build with `mvn package` or `gradle build`
3. Run the `Main` class

## Future Enhancements

1. Database persistence for products and customers
2. REST API interface
3. User authentication
4. Discount and promotion system
5. Order history tracking
