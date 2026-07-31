# Solution Walkthrough: Microservices Decomposition (Module 14)

**Focus:** Building the Inventory Service Endpoint
**Target Length:** 5 - 7 minutes
**Files:** `InventoryController.java`, `application.properties`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the starter project structure)*

"Welcome back. In this video, we're going to walk through the solution to the Microservices Decomposition exercise.

"Our goal was to build the Inventory Service endpoint so the Order Service could call it to check product availability. The starter code gave us two empty Spring Boot applications — we needed to add the controller, the response record, and configure the ports.

"Let's walk through each step."

## 1:00 – 2:30 | Steps 1-3: The Inventory Controller

*(Switch tabs to `InventoryController.java`)*

"Steps 1 through 3 are about building the `InventoryController` in the inventory-service.

"The controller is annotated with `@RestController`. Inside, we define an `InventoryResponse` record with two fields: `productId` and `inStock`. Using a Java record here means we get an immutable data class with automatic JSON serialization — no boilerplate.

"The endpoint is `GET /inventory/{productId}`. We take the product ID as a `@PathVariable`. For the implementation, we have a simple stub: the item is in stock unless the ID equals 'OUT_OF_STOCK_ID'. In a real application, this would query a database.

"We return the `InventoryResponse` record, and Spring's Jackson integration automatically serializes it to JSON."

## 2:30 – 3:30 | Steps 5-6: Port Configuration

*(Switch tabs to `inventory-service/src/main/resources/application.properties`)*

"Steps 5 and 6 are configuring the ports. In the inventory-service's `application.properties`, we set `server.port=8082`.

*(Switch tabs to `order-service/src/main/resources/application.properties`)*

"In the order-service's `application.properties`, we set `server.port=8083`. These must be different so both services can run simultaneously on the same machine."

## 3:30 – 5:00 | Running and Testing

*(🖥️ Terminal 1: `cd inventory-service && mvn spring-boot:run`)*

"Let's start both services. In the first terminal, the Inventory Service starts on port 8082.

*(🖥️ Terminal 2: `cd order-service && mvn spring-boot:run`)*

"In the second terminal, the Order Service starts on port 8083.

*(🖥️ Terminal 3: `curl -s http://localhost:8082/inventory/PROD-123 | jq`)*

"Let's test the inventory endpoint directly. `curl http://localhost:8082/inventory/PROD-123` returns `productId: "PROD-123"` and `inStock: true`. Our controller is working.

*(🖥️ Terminal: `curl -s http://localhost:8082/inventory/OUT_OF_STOCK_ID | jq`)*

"And `OUT_OF_STOCK_ID` returns `inStock: false`. The stub logic is correct.

"With this inventory endpoint in place, the Order Service can now call it over HTTP before placing orders — completing the microservices communication pattern."

## 5:00 – 5:30 | Outro

"To summarize: We built an independently deployable Inventory Service with a REST endpoint that returns inventory status. Each service runs on its own port, and they communicate over HTTP. This is the core pattern of microservices architecture — separate deployment, separate scaling, and well-defined API contracts.

"Great job if you got this working. I'll see you in the next module."
