# Module 6 - Request/Response Handling and DTOs - Exercise Instructions

## Exercise Overview

Your APIs are currently exposing internal database entities directly to the client, leading to over-fetching and tight coupling. You need to implement Data Transfer Objects (DTOs) and use MapStruct to map between your internal domain models and the external API contracts.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/order/OrderController.java`, refactor the endpoints to accept and return DTOs instead of raw Entities.
2. In `src/main/java/com/ecommerce/order/OrderMapper.java`, define the MapStruct mapping rules.


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
