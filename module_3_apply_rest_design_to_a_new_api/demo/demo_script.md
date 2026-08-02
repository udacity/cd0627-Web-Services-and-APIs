# Demo Walkthrough: REST Principles and HTTP Methods (Module 3)

**Focus:** Resource Mapping, Status Codes, and PATCH vs. POST for State Changes
**Target Length:** 5 - 7 minutes
**File:** `ProductController.java`

---

## 0:00 – 1:00 | Introduction & Scenario

*(Screen showing `ProductController.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at RESTful API design using Spring Boot.

"Our scenario today is an e-commerce Product API. We need to create endpoints that follow REST conventions — proper resource naming, correct HTTP methods, and accurate status codes. We are going to walk through three key concepts: resource mapping, status codes and headers, and the difference between PATCH and POST for state changes.

"Let's jump into the code."

## 1:00 – 2:30 | Step 1: Resource Mapping

*(Highlight lines 41-43: `@RestController` and `@RequestMapping("/products")`)*

"The first thing to notice is our class-level annotation. `@RequestMapping("/products")` sets the base path. In REST, the collection resource is always the plural noun — `/products`, not `/getProducts` or `/productList`. REST uses HTTP methods as the verbs, so there should be no verbs in the URI itself.

*(Highlight lines 64-67: `listProducts()` method)*

"Our first endpoint is `@GetMapping` with no additional path. This maps to `GET /products` and returns all products. Simple.

*(Highlight lines 83-88: `getProduct()` method)*

"Our second endpoint adds `/{id}` to the path. `GET /products/1` addresses a specific product. Notice what happens when the product doesn't exist — we return `ResponseEntity.notFound().build()`, which is a 404. Returning an empty 200 would be a lie; 404 is the honest, unambiguous answer."

## 2:30 – 4:00 | Step 2: Status Codes & the Location Header

*(Highlight lines 123-143: `createProduct()` method)*

"Now let's look at Step 2 — creating a new product with `POST /products`.

"A common mistake is returning 200 OK for creation. The problem is, the client has no way to tell whether a new resource was actually created or we just returned an existing one. The fix involves two things.

*(Highlight lines 140-142: `ResponseEntity.created(location).body(created)`)*

"First, we return status 201 Created, which explicitly signals that a new resource was made. Second, we include a `Location` header that tells the client exactly where to find the newly created product.

"We use Spring's `UriComponentsBuilder` to construct the URI — `/products/4`, for example — and then `ResponseEntity.created(location)` sets both the 201 status and the Location header automatically."

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s -i -X POST http://localhost:8080/products -H "Content-Type: application/json" -d '{"name":"Keyboard","description":"Mechanical","price":99.99}'`)*

"Let's run the app and test this with curl. Looking at the response headers, we can see `HTTP/1.1 201 Created` and `Location: /products/4`. Every major API — GitHub, Stripe, AWS — follows this exact pattern."

## 4:00 – 5:30 | Step 3: PATCH vs. POST for State Changes

*(Highlight lines 176-184: `patchProduct()` method)*

"Finally, let's look at Step 3: partial updates with PATCH.

"Here we have `@PatchMapping("/{id}")`. PATCH is perfect when you are changing a simple data field — like updating a product's price. The client only sends the field it wants to change. We merge it with what we already have stored.

*(🖥️ Terminal: `curl -s -X PATCH http://localhost:8080/products/1 -H "Content-Type: application/json" -d '{"price":129.99}'`)*

"Let's try it. We send a PATCH to `/products/1` with just the new price. Looking at the response, the product's price is updated to 129.99, and all other fields are preserved.

"Now, here is the critical distinction. PATCH works here because this is a pure data field update — there are no side effects. But what about cancelling an order? Cancellation triggers refunds, inventory restocking, notification emails. PATCH does not communicate that level of business intent. For actions with side effects, we use POST with a named action path — like `POST /orders/{id}/cancel`. You will see exactly this pattern in the exercise."

## 5:30 – 6:00 | Outro & Summary

"To summarize the three key REST conventions we covered:
1. Use plural nouns for resource URIs — no verbs in the path.
2. Return 201 Created with a Location header for creation endpoints.
3. Use PATCH for data field updates, and POST with a named action path for business operations with side effects.

"Thanks for watching, and I'll see you in the next module."
