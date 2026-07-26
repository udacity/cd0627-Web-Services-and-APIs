# Module 6 — Request/Response Handling and DTOs — Exercise Instructions

## Exercise Overview

Your APIs are currently exposing internal database entities directly to the client, leading to over-fetching and tight coupling. You need to implement Data Transfer Objects (DTOs) using Java Records and use MapStruct to map between your internal domain models and the external API contracts.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. **Create `OrderResponse` Record** (Step 1) — Define a Java record with fields: `id`, `totalAmount`, `status`. This is the DTO returned to the client.

2. **Create `CreateOrderRequest` Record** (Step 2) — Define a Java record with fields: `totalAmount`, `status`, `deliveryDate`, `itemIds`. This is the DTO accepted from the client.

3. **Add Validation to `CreateOrderRequest`** (Step 3) — Apply Bean Validation annotations (e.g., `@NotNull`, `@NotEmpty`, `@FutureOrPresent`) to the request fields.

4. **Create `OrderMapper` interface using MapStruct** (Step 4) — Define a `@Mapper` interface with methods to convert between the `Order` entity and `OrderResponse`/`CreateOrderRequest` DTOs.

5. **Refactor the endpoints** (Step 5) — Update the controller to use the Records, `@Valid`, and the generated Mapper instead of raw entities.

> [!NOTE]
> MapStruct generates the implementation class at compile time. After creating the interface, run `mvn compile` to generate the mapper implementation.

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

- [ ] Endpoints accept `CreateOrderRequest` and return `OrderResponse` (no raw entities exposed).
- [ ] MapStruct generates the implementation class at compile time.
- [ ] Invalid requests are rejected with proper validation errors.
- [ ] Internal fields (e.g., `internalMargin`) are not leaked to the client.
