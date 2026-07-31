# Solution Walkthrough: GraphQL Fundamentals (Module 12)

**Focus:** Solving the N+1 Problem with @BatchMapping and Adding Mutations
**Target Length:** 5 - 7 minutes
**Files:** `OrderController.java`, `schema.graphqls`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `OrderController.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the GraphQL exercise.

"Our goal was to implement a GraphQL API for orders. We had three steps: first, create a naïve `@SchemaMapping` for the `customer` field to see the N+1 problem. Then, replace it with a `@BatchMapping` to solve it. Finally, add a `createOrder` mutation.

"Let's start by looking at the schema."

## 1:00 – 2:00 | The Schema

*(Switch tabs to `schema.graphqls`)*

"Our schema defines an `Order` type with `id`, `totalAmount`, `status`, and a nested `customer` field. The `Customer` type has `id`, `name`, and `email`. We have a `query` for listing orders and a `mutation` for creating them.

"The important part is that `customer` is a nested type — GraphQL needs a separate resolver to fetch it. How we resolve it makes the difference between an N+1 disaster and a performant API."

## 2:00 – 3:30 | Steps 1 & 2: From N+1 to @BatchMapping

*(Switch tabs to `OrderController.java`, highlight lines 37-49: `customer()` @BatchMapping method)*

"In Step 1, we would have started with a `@SchemaMapping` that fetches one customer at a time — the N+1 problem. For Step 2, we replaced it with `@BatchMapping`.

"Look at the method signature. Instead of accepting a single `Order`, it accepts `List<Order>` — the entire batch. And instead of returning a single `Customer`, it returns `Map<Order, Customer>`.

"Here is how it works. We extract all the `customerId` values from the order list. We call `customerRepository.findAllByIds()` — a single query that fetches all customers at once. Then we build a map from each order to its customer.

"The logs tell the story. If we query 5 orders, we see a single log line: 'BatchMapping triggered for 5 orders.' Not 5 individual calls — just one batch."

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Browser: `http://localhost:8080/graphiql`)*

*(In GraphiQL, run: `{ orders { id totalAmount status customer { name email } } }`)*

"Let's test it in GraphiQL. We query all orders with their nested customer data. Every order has its customer name and email resolved in a single batch call. The N+1 problem is completely eliminated."

## 3:30 – 4:30 | Step 3: The createOrder Mutation

*(Highlight lines 52-56: `createOrder()` method)*

"Step 3 is the mutation. `createOrder` is annotated with `@MutationMapping`. It accepts `totalAmount` and `customerId` as `@Argument` parameters, creates a new `Order` with a 'PENDING' status, saves it to the repository, and returns it.

*(In GraphiQL, run: `mutation { createOrder(totalAmount: 250.00, customerId: 2) { id totalAmount status customer { name } } }`)*

"Let's test it. We create a new order for customer 2 with a total of 250 dollars. GraphQL returns the created order with the ID, status 'PENDING', and the customer's name — all in a single response. If we re-run the orders query, the new order appears."

## 4:30 – 5:00 | Outro

"To summarize: We solved the N+1 problem by replacing individual `@SchemaMapping` calls with a single `@BatchMapping` that resolves all nested objects in one batch. We added a mutation for creating orders. And the client controls exactly which fields come back in every response.

"Great job if you got this working. I'll see you in the next module."
