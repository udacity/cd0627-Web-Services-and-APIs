# Module 12 — GraphQL Fundamentals — Exercise Instructions

## Exercise Overview

Your frontend team is frustrated by over-fetching and under-fetching REST endpoints. You must implement a GraphQL API that allows the client to request exactly the data they need in a single round-trip.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/graphql/OrderController.java`, implement a **naïve `@SchemaMapping`** for the `customer` field on `Order` (Step 1). This resolves each order's customer individually — observe the N+1 query problem in the logs.

2. Once you see the N+1 problem, **comment out the `@SchemaMapping`** and replace it with a **`@BatchMapping`** (Step 2). This resolves all customers in a single batch call, eliminating the N+1 issue.

3. Add a **`@MutationMapping`** for `createOrder` (Step 3). Accept `totalAmount` and `customerId` as `@Argument` parameters, create a new order, and return it.

> [!NOTE]
> The `@QueryMapping` for `orders()` is already implemented. The schema is defined in `src/main/resources/graphql/schema.graphqls`.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

Open GraphiQL at `http://localhost:8080/graphiql` and try:
```graphql
query {
  orders {
    id
    totalAmount
    status
    customer {
      name
      email
    }
  }
}
```

---

## Success Criteria

- [ ] The `orders` query resolves with nested `customer` data.
- [ ] `@BatchMapping` eliminates the N+1 problem (single batch log entry instead of per-order).
- [ ] The `createOrder` mutation creates a new order and returns it.
- [ ] The GraphiQL interface is accessible at `/graphiql`.
