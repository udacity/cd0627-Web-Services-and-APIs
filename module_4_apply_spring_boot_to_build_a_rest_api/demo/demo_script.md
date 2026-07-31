# Demo Walkthrough: Building REST APIs with Spring Boot (Module 4)

**Focus:** Spring Boot Auto-Configuration, ResponseEntity, API Versioning, and Declarative HTTP Clients
**Target Length:** 5 - 7 minutes
**Files:** `ProductController.java`, `InventoryClient.java`, `DemoApplication.java`

---

## 0:00 – 1:00 | Introduction & Scenario

*(Screen showing `ProductController.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at how Spring Boot simplifies building REST APIs.

"Our scenario is a Product API. But this time, we are going beyond basic CRUD. We are going to cover three things: how Spring Boot's auto-configuration eliminates boilerplate, how to implement API versioning using headers, and how to call a downstream service using Spring's declarative HTTP interface.

"Let's start with the basics."

## 1:00 – 2:30 | Step 1: CRUD with ResponseEntity

*(Highlight lines 9-10: `@RestController` and `@RequestMapping("/products")`)*

"The foundation is familiar — `@RestController` and `@RequestMapping("/products")`. Spring Boot auto-configures the embedded Tomcat server, the Jackson JSON serializer, and the servlet container. We do not write a single line of configuration for any of that.

*(Highlight lines 20-25: `getProduct()` method)*

"Our `GET /products/{id}` endpoint returns a `ResponseEntity<Map>`. Notice we are using `ResponseEntity.ok()`, which gives us fine-grained control over the HTTP status code. The response includes the product ID, a name, and — notice this — an inventory status that we are fetching from a separate service. We will come back to how that works in a moment.

*(Highlight lines 27-30: `createProduct()` method)*

"For `POST /products`, we return `ResponseEntity.created()` with a Location header — status 201 signals a new resource was created, and the header tells the client where to find it. Spring Boot handles the JSON serialization of the response body automatically."

## 2:30 – 3:30 | Step 2: API Versioning via Headers

*(Highlight lines 33-36: `getProductV2()` method)*

"Now let's look at API versioning. In a real product, the V1 response might return basic fields, but a V2 response adds new fields like 'features'. We do not want to break existing clients.

"Notice the mapping: `@GetMapping(path = "/{id}", headers = "version=2")`. This tells Spring to route the request to this method only when the client includes a `version=2` header. Without that header, the V1 method handles the request.

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/products/1 | jq`)*

"Without the header, we get the V1 response — just `id`, `name`, and `inventory`.

*(🖥️ Terminal: `curl -s -H "version: 2" http://localhost:8080/products/1 | jq`)*

"With the `version: 2` header, we get V2 — the same fields plus `features`. Same URL, different contract, no breaking changes."

## 3:30 – 5:30 | Step 3: Declarative HTTP Interface

*(Switch tabs to `InventoryClient.java`)*

"This brings us to the most powerful feature of this module: the declarative HTTP interface.

"Look at `InventoryClient`. It is a plain Java interface with a single method — `getInventoryStatus()` — annotated with `@GetExchange("/internal/inventory/{id}")`. There is no implementation class. No `RestTemplate`. No manual URL building.

"Spring generates the implementation at runtime. We just declare what we want to call, and Spring handles the HTTP connection, serialization, and error handling.

*(Switch tabs to `DemoApplication.java`, highlight lines 17-23)*

"But the interface needs to be wired up. In `DemoApplication.java`, we have a `@Bean` method that creates the proxy. We build a `RestClient` with a base URL, wrap it in a `RestClientAdapter`, and then pass it to `HttpServiceProxyFactory`. The factory generates a live implementation of our `InventoryClient` interface.

"Now any controller can inject `InventoryClient` just like any other Spring bean and call `getInventoryStatus()` — and it makes a real HTTP call behind the scenes.

*(Switch tabs to `InternalInventoryController.java`)*

"For this demo, the downstream 'inventory service' is just a simple controller in the same application that returns a hardcoded JSON response. In production, this would be a completely separate microservice."

## 5:30 – 6:00 | Outro & Summary

"To summarize what Spring Boot gave us:
1. Zero-configuration auto-setup — embedded server, JSON serialization, all automatic.
2. Header-based API versioning — same URL, different response based on the `version` header.
3. Declarative HTTP interfaces — write an interface, annotate the method, and Spring generates the HTTP client.

"Thanks for watching, and I'll see you in the next module."
