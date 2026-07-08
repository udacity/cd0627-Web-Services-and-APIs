# Module 6 - Request/Response Handling and DTOs - Exercise Instructions

## Exercise Overview

Your APIs are currently exposing internal database entities directly to the client, leading to over-fetching and tight coupling. You need to implement Data Transfer Objects (DTOs) and use MapStruct to map between your internal domain models and the external API contracts.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1. Create OrderResponse Record (id, totalAmount, status) | `src/main/java/com/ecommerce/order/OrderController.java` |
| 2 | 2. Create CreateOrderRequest Record (totalAmount, status, deliveryDate, itemIds) | `src/main/java/com/ecommerce/order/OrderController.java` |
| 3 | 3. Add Validation to CreateOrderRequest: | `src/main/java/com/ecommerce/order/OrderController.java` |
| 4 | 4. Create OrderMapper interface using MapStruct. | `src/main/java/com/ecommerce/order/OrderController.java` |
| 5 | 5. Refactor the endpoints below to use the Records, `@Valid`, and the Mapper. | `src/main/java/com/ecommerce/order/OrderController.java` |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Endpoints only return DTOs.
- [ ] MapStruct generates the implementation class at compile time.
- [ ] Data is isolated from the domain layer.
