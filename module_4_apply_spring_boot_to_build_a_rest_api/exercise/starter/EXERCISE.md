# Module 4 - Building REST APIs with Spring Boot - Exercise Instructions

## Exercise Overview

Your startup needs a REST API to manage Customers. You need to build a Spring Boot REST API that handles CRUD operations for a Customer entity, utilizes API versioning, and communicates with a downstream service using an HTTP client.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/order/controller/OrderController.java`, annotate the class with `@RestController` and `@RequestMapping` to handle web requests.
2. Implement a GET mapping to retrieve a customer by ID.
3. Implement a POST mapping to create a new customer, returning a 201 Created status.


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

- [ ] HTTP GET returns customer data.
- [ ] HTTP POST successfully creates a resource.
- [ ] HTTP interface is configured correctly.
