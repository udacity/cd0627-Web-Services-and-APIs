# Demo Walkthrough: GraphQL with Spring for GraphQL (Module 13)

**Focus:** From REST Over-Fetching to GraphQL Precision — and the N+1 Trap
**Target Length:** 5 - 7 minutes
**Files:** `ProductController.java`, `schema.graphqls`

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing `schema.graphqls` open in the IDE)*

"Welcome back. In this demo, we are going to look at GraphQL with Spring for GraphQL.

"The problem GraphQL solves is over-fetching. With REST, when a client requests a product, they get every field — ID, name, price, supplier — even if they only need the name. With a list of 100 products, that is a lot of wasted bandwidth. GraphQL lets the client request exactly the fields they need.

"Let's look at our schema. We have a `Product` type with `id`, `name`, `price`, and a nested `supplier` field. Our query exposes a `products` list, and we have a `createProduct` mutation."

## 1:00 – 2:30 | Step 1: Queries and Mutations

*(Switch tabs to `ProductController.java`, highlight lines 28-31: `products()` method)*

"In Spring for GraphQL, resolvers are annotated with `@QueryMapping` and `@MutationMapping`. Our `products()` method is annotated with `@QueryMapping` and simply returns the list. Spring handles the JSON serialization and field selection automatically.

*(Highlight lines 33-38: `createProduct()` mutation)*

"The `createProduct` mutation uses `@MutationMapping`. The parameters `name` and `price` are annotated with `@Argument`, which maps them from the GraphQL input. We create a new product and return it.

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Browser: `http://localhost:8080/graphiql`)*

"Let's run the app and open GraphiQL — the built-in GraphQL IDE. If we query just `{ products { name } }`, we get only the names. If we add `price`, we get names and prices. The client controls exactly what data comes back."

## 2:30 – 4:00 | Step 2: The Nested Resolver and the N+1 Problem

*(Highlight lines 42-47: `supplier()` @SchemaMapping method)*

"Now, look at the `supplier` field. It is resolved by a separate method annotated with `@SchemaMapping(typeName = "Product", field = "supplier")`. When the client requests the `supplier` field, Spring calls this method once for each product in the list.

"Notice the log statement. If we query 10 products and ask for their suppliers, this method fires 10 times. That is 10 separate database calls — this is the N+1 problem. We loaded 1 list of products, then made N additional calls for the suppliers.

*(In GraphiQL, run the query: `{ products { name supplier { name } } }`)*

*(Highlight the server logs showing 10 log lines)*

"Looking at the logs, we can see 'Fetching supplier for product ID' printed 10 times. In a real application with thousands of products, this would be devastating to performance.

"In the exercise, you will solve this exact problem using `@BatchMapping`, which collapses all those individual calls into a single batch query."

## 4:00 – 5:00 | Step 3: Mutations in Action

*(In GraphiQL, run the mutation: `mutation { createProduct(name: "New Widget", price: 49.99) { id name price } }`)*

"Let's test the mutation. We create a new product named 'New Widget' at 49.99. GraphQL returns exactly the fields we asked for: `id`, `name`, and `price`. If we re-run the `products` query, we see our new product at the end of the list."

## 5:00 – 5:30 | Outro & Summary

"To summarize: GraphQL eliminates over-fetching by letting the client choose exactly which fields to retrieve. `@QueryMapping` and `@MutationMapping` wire resolvers to the schema. But watch out for `@SchemaMapping` on nested types — it creates the N+1 problem. The solution is `@BatchMapping`, which you will implement in the exercise.

"Thanks for watching, and I'll see you in the next module."
