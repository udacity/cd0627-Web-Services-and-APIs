# Module 12 - GraphQL Fundamentals - Exercise Instructions

## Exercise Overview

Your frontend team is frustrated by over-fetching and under-fetching REST endpoints. You must implement a GraphQL API that allows the client to request exactly the data they need in a single round-trip.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1: Implement a naïve `@SchemaMapping` for "customer" on "Order". | `src/main/java/com/ecommerce/graphql/OrderController.java` |
| 2 | 2: Once you see the N+1 problem, comment out the `@SchemaMapping` and replace it with `@BatchMapping`. | `src/main/java/com/ecommerce/graphql/OrderController.java` |


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

- [ ] The GraphQL endpoint resolves complex queries.
- [ ] Clients can specify exact fields to retrieve.
- [ ] The GraphiQL interface is accessible.
