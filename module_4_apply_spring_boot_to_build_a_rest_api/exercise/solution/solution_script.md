# Solution Walkthrough: Building REST APIs with Spring Boot (Module 4)

**Focus:** Order API with CRUD, Versioning, and the CustomerClient HTTP Interface
**Target Length:** 5 - 7 minutes
**Files:** `OrderController.java`, `CustomerClient.java`, `OrderApiApplication.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `OrderController.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Spring Boot REST API exercise.

"Our goal was to build an Order API with four endpoints: get an order by ID, create an order, cancel an order, and a versioned get endpoint. We also needed to create a declarative HTTP interface to call a downstream Customer service.

"Let's walk through each step."

## 1:00 – 2:30 | Step 1: GET /orders/{id} with CustomerClient

*(Highlight lines 19-23: `getOrder()` method)*

"Step 1 is `GET /orders/{id}`. We use `@GetMapping("/{id}")` with `@PathVariable` to capture the order ID.

"The interesting part is this line: `customerClient.getCustomerName(id)`. We are calling a downstream Customer service to enrich the response with the customer's name. The response includes the `id`, `status`, and `customerName` — all returned with a 200 OK via `ResponseEntity.ok()`.

"This demonstrates a common pattern in microservices: fetching data from one service to enrich the response of another."

## 2:30 – 3:30 | Steps 2 & 3: POST Endpoints

*(Highlight lines 25-28: `createOrder()` method)*

"Step 2 is `POST /orders`. We return `ResponseEntity.created()` with a Location header pointing to `/orders/1`. This gives the client a 201 Created status and tells it where to find the newly created resource.

*(Highlight lines 30-36: `cancelOrder()` method)*

"Step 3 is `POST /orders/{id}/cancel`. We have a simple mock check: if the order ID is greater than 100, we treat it as 'not found' and return 404. Otherwise, we return 204 No Content, which is the appropriate status for a successful action that does not return a body."

## 3:30 – 4:30 | Step 4: API Versioning

*(Highlight lines 38-42: `getOrderV2()` method)*

"Step 4 is the versioned endpoint. We add a second `@GetMapping` for the same path `/{id}`, but with `headers = "version=2"`. Spring routes the request based on whether the client includes this header.

"The V2 response adds an `orderSummary` field. This is clean versioning — existing clients continue hitting V1 without any change, and new clients opt in to V2 by adding the header."

## 4:30 – 5:30 | Step 5: Declarative HTTP Interface

*(Switch tabs to `CustomerClient.java`)*

"Step 5 is the declarative HTTP interface. Look at `CustomerClient.java` — it is a plain Java interface. The method `getCustomerName()` is annotated with `@GetExchange("/internal/customers/{id}")`. No implementation code, no boilerplate.

*(Switch tabs to `OrderApiApplication.java`, highlight lines 17-22)*

"In `OrderApiApplication.java`, we wire it up. We build a `RestClient` with `RestClient.builder().baseUrl("http://localhost:8080")`, create a `RestClientAdapter`, and use `HttpServiceProxyFactory` to generate the implementation of our `CustomerClient` interface.

"Spring now generates a proxy that makes real HTTP calls whenever `getCustomerName()` is invoked."

## 5:30 – 6:30 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

"Let's run the application and verify everything works.

*(🖥️ Terminal: `curl -s http://localhost:8080/orders/1 | jq`)*

"First, `GET /orders/1` — we see the order data with a `customerName` field pulled from the downstream service.

*(🖥️ Terminal: `curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8080/orders`)*

"Next, `POST /orders` returns a 201 Created.

*(🖥️ Terminal: `curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8080/orders/50/cancel`)*

"Cancelling order 50 returns 204 No Content.

*(🖥️ Terminal: `curl -s -o /dev/null -w "%{http_code}" -X POST http://localhost:8080/orders/200/cancel`)*

"Cancelling order 200 returns 404 Not Found — our mock check works.

*(🖥️ Terminal: `curl -s -H "version: 2" http://localhost:8080/orders/1 | jq`)*

"And with the `version: 2` header, we get the enriched V2 response with `orderSummary`."

## 6:30 – 7:00 | Outro

"All five steps are working. We built a complete REST API with proper status codes, header-based versioning, and a declarative HTTP interface to call a downstream service — all without writing a single line of HTTP client boilerplate.

"Great job if you got this working. I'll see you in the next module."
