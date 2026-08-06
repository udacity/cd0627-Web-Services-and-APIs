# Demo Walkthrough: Microservices Decomposition (Module 15)

**Focus:** From Monolith to Two Independent Services Communicating Over HTTP
**Target Length:** 5 - 7 minutes
**Files:** `UserController.java`, `UserClient.java`, `ClientConfig.java`, `OrderController.java`

---

## 0:00 – 1:00 | Introduction & Scenario

*(Screen showing the project structure with two sub-projects: `user-service` and `order-service`)*

"Welcome back. In this demo, we are going to look at Microservices Decomposition — splitting a monolith into independently deployable services.

"Our scenario is straightforward: we have an Order Service that needs to look up customer details before placing an order. In a monolith, this would be a local method call. In a microservices architecture, these are two separate applications, each running on its own port, communicating over HTTP.

"Let's see how the two services are built and how they talk to each other."

## 1:00 – 2:00 | The User Service

*(Switch tabs to `UserController.java` in the user-service)*

"Let's start with the User Service. It is a minimal Spring Boot application running on port 8081.

"The `UserController` has one endpoint: `GET /users/{id}`. It takes a user ID as a path variable and returns a JSON map with `id`, `name`, and `email`.

"For this demo, the data is stubbed — every request returns the same user. In production, this would query a real user database."

## 2:00 – 3:30 | The Order Service and the HTTP Client

*(Switch tabs to `UserClient.java` in the order-service)*

"Now let's look at the Order Service. It needs to call the User Service to get customer details before placing an order. We use Spring's declarative HTTP interface.

"`UserClient` is a plain Java interface with `@GetExchange("/users/{id}")`. It also defines a `UserResponse` record. Spring generates the HTTP client implementation at runtime — no `RestTemplate` boilerplate needed.

*(Switch tabs to `ClientConfig.java`)*

"In `ClientConfig.java`, we wire the client to point at `http://localhost:8081` — the User Service's address. The `RestClient`, adapter, and proxy factory create the live implementation.

*(Switch tabs to `OrderController.java`)*

"And here is the `OrderController`. When a `POST /orders` request comes in with a `userId` parameter, we call `userClient.getUser(userId)`. The user's name and email are included in the order confirmation response.

"This is the 'network bridge' — what used to be a local method call is now an HTTP request to a separate service."

## 3:30 – 5:00 | Running Two Services

*(🖥️ Terminal 1: `cd user-service && mvn spring-boot:run`)*

"Let's see it in action. We need two terminals. In the first terminal, we start the User Service on port 8081.

*(🖥️ Terminal 2: `cd order-service && mvn spring-boot:run`)*

"In the second terminal, we start the Order Service on port 8082. Both are now running independently.

*(🖥️ Terminal 3: `curl -s http://localhost:8081/users/1 | jq`)*

"First, let's verify the User Service directly. `curl /users/1` returns the user JSON with `id`, `name`, and `email`.

*(🖥️ Terminal: `curl -s -X POST "http://localhost:8082/orders?userId=1" | jq`)*

"Now, the Order Service. `POST /orders?userId=1` — the order service calls the user service over HTTP, gets the customer details, and returns 'Order placed successfully' along with the customer name and email."

## 5:00 – 5:30 | Outro & Summary

"To summarize: We decomposed a monolith into two independently deployable services. Each runs on its own port with its own application context. They communicate over HTTP using Spring's declarative HTTP interface. This is the foundation of a microservices architecture.

"Thanks for watching, and I'll see you in the next module."
