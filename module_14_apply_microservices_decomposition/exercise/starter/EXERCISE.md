# Module 14 - Microservices Architecture Principles - Exercise Instructions

## Exercise Overview

You are decomposing a monolith. You need to extract the Order and Inventory logic into separate, independently deployable microservices that communicate over HTTP.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In the `order-service` project, implement an HTTP client to verify inventory via the `inventory-service`. |
| 2 | Ensure the services can run on separate ports simultaneously. |


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

- [ ] The Order service successfully calls the Inventory service.
- [ ] Both services run independently.
