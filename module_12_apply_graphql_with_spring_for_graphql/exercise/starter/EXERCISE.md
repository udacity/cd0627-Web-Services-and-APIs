# Module 12 - GraphQL Fundamentals - Exercise Instructions

## Exercise Overview

Your frontend team is frustrated by over-fetching and under-fetching REST endpoints. You must implement a GraphQL API that allows the client to request exactly the data they need in a single round-trip.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. Review the pre-defined Types, Queries, and Mutations in `src/main/resources/graphql/schema.graphqls`.
2. In `src/main/java/com/ecommerce/graphql/OrderController.java`, annotate query methods with `@QueryMapping` and mutation methods with `@MutationMapping`.
3. Annotate nested field resolvers with `@SchemaMapping`.


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
- [ ] Clients can create new orders via mutations.
- [ ] The GraphiQL interface is accessible.
