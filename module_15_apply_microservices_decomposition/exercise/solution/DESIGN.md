# Microservices Design

## Monolithic Requirement
"When a user places an order, we need to check if the item is in stock, then process the payment, and finally notify them."

## 1. Domain Ownership
Who owns the "Stock Count" data?
**Inventory Service**

Who owns the "Checkout State" data?
**Order Service**

## 2. Communication Style
Should checking stock during checkout be Synchronous (REST) or Asynchronous (Kafka)? Why?
**Synchronous (REST)**, because the checkout process cannot logically proceed if the item is out of stock. We need an immediate, strict validation response from the Inventory domain before accepting the order.
