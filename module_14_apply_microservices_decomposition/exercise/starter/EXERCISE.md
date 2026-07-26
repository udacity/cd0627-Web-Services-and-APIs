# Module 14 — Microservices Architecture Principles — Exercise Instructions

## Exercise Overview

You are decomposing a monolith. You need to extract the Order and Inventory logic into separate, independently deployable microservices that communicate over HTTP.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Design Exercise

Before writing code, review `DESIGN.md` and answer the design questions about domain ownership and communication style. This will help you understand the architectural decisions behind the decomposition.

---

## Step-by-Step Implementation Guide

### Inventory Service (`inventory-service/`)

1. In `src/main/java/com/ecommerce/inventory/InventoryController.java`, add the `@RestController` annotation (Step 1 — already provided as scaffold).

2. Create an `InventoryResponse` record with `productId` (String) and `inStock` (boolean) fields (Step 2).

3. Implement `GET /inventory/{productId}` — return an `InventoryResponse` with `inStock: true` as a stub (Step 3).

4. Replace the stub with real inventory lookup logic (Step 4 — optional stretch goal).

### Configuration

5. In `inventory-service/src/main/resources/application.properties`, configure the server port to **8082**.

6. In `order-service/src/main/resources/application.properties`, configure the server port to **8083**.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

Start each service in a **separate terminal**:

```bash
# Terminal 1 — Inventory Service
cd inventory-service
mvn spring-boot:run
```

```bash
# Terminal 2 — Order Service
cd order-service
mvn spring-boot:run
```

Test the Inventory Service:
```bash
curl http://localhost:8082/inventory/PROD-123
```

---

## Success Criteria

- [ ] The Inventory Service responds at `http://localhost:8082/inventory/{productId}`.
- [ ] Both services run independently on separate ports.
- [ ] The response includes `productId` and `inStock` fields.
