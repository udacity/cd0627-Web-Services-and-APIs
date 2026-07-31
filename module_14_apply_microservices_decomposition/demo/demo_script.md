# Demo Walkthrough: Microservices Decomposition (Module 14)

**Focus:** From Monolith to Two Independent Services Communicating Over HTTP
**Target Length:** 5 - 7 minutes
**Files:** `OrderController.java`, `InventoryClient.java`, `InventoryController.java`

---

## 0:00 – 1:00 | Introduction & Scenario

*(Screen showing the project structure with two sub-projects: `order-service` and `inventory-service`)*

"Welcome back. In this demo, we are going to look at Microservices Decomposition — splitting a monolith into independently deployable services.

"Our scenario is straightforward: we have an Order Service that needs to check product inventory before placing an order. In a monolith, this would be a local method call. In a microservices architecture, these are two separate applications, each running on its own port, communicating over HTTP.

"Let's see how the two services are built and how they talk to each other."

## 1:00 – 2:30 | The Inventory Service

*(Switch tabs to `InventoryController.java` in the inventory-service)*

"Let's start with the Inventory Service. It is a minimal Spring Boot application running on port 8082.

"The `InventoryController` has one endpoint: `GET /inventory/{productId}`. It takes a product ID as a path variable and returns an `InventoryResponse` record with the product ID and a boolean `inStock` flag.

"For this demo, the logic is stubbed — every product is in stock unless the ID is 'OUT_OF_STOCK_ID'. In production, this would query a real inventory database."

## 2:30 – 4:00 | The Order Service and the HTTP Client

*(Switch tabs to `InventoryClient.java` in the order-service)*

"Now let's look at the Order Service. It needs to call the Inventory Service before placing an order. We use Spring's declarative HTTP interface — a plain Java interface annotated with `@GetExchange`, where Spring generates the HTTP client implementation at runtime.

"`InventoryClient` is a plain Java interface with `@GetExchange("/inventory/{productId}")`. Spring generates the HTTP client implementation at runtime.

*(Switch tabs to `ClientConfig.java`)*

"In `ClientConfig.java`, we wire the client to point at `http://localhost:8082` — the Inventory Service's address. The `RestClient`, adapter, and proxy factory create the live implementation.

*(Switch tabs to `OrderController.java`)*

"And here is the `OrderController`. When a `POST /orders` request comes in, we call `inventoryClient.checkInventory(productId)`. If the item is not in stock, we return 400 Bad Request. Otherwise, we return 200 with a success message.

"This is the 'network bridge' — what used to be a local method call is now an HTTP request to a separate service."

## 4:00 – 5:30 | Running Two Services

*(🖥️ Terminal 1: `cd inventory-service && mvn spring-boot:run`)*

"Let's see it in action. We need two terminals. In the first terminal, we start the Inventory Service on port 8082.

*(🖥️ Terminal 2: `cd order-service && mvn spring-boot:run`)*

"In the second terminal, we start the Order Service on port 8083. Both are now running independently.

*(🖥️ Terminal 3: `curl -s http://localhost:8082/inventory/PROD-123 | jq`)*

"First, let's verify the Inventory Service directly. `curl /inventory/PROD-123` returns `inStock: true`.

*(🖥️ Terminal: `curl -s -X POST "http://localhost:8083/orders?productId=PROD-123"`)*

"Now, the Order Service. `POST /orders?productId=PROD-123` — the order service calls the inventory service over HTTP, gets the 'in stock' confirmation, and returns 'Order placed successfully.'

*(🖥️ Terminal: `curl -s -X POST "http://localhost:8083/orders?productId=OUT_OF_STOCK_ID"`)*

"And with an out-of-stock product, we get 'Item is out of stock.' The Order Service correctly delegates the inventory check to the Inventory Service."

## 5:30 – 6:00 | Outro & Summary

"To summarize: We decomposed a monolith into two independently deployable services. Each runs on its own port with its own application context. They communicate over HTTP using Spring's declarative HTTP interface. This is the foundation of a microservices architecture.

"Thanks for watching, and I'll see you in the next module."
