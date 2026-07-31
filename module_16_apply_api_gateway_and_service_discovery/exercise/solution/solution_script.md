# Solution Walkthrough: API Gateway and Service Discovery (Module 16)

**Focus:** Wiring Up Eureka, Gateway Routes, and Proving Load Balancing
**Target Length:** 5 - 7 minutes
**Files:** `EurekaServerApplication.java`, `ApiGatewayApplication.java`, `application.yml`, `OrderController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the three sub-projects)*

"Welcome back. In this video, we're going to walk through the solution to the API Gateway and Service Discovery exercise.

"Our goal was to wire up three components: a Eureka service registry, an API gateway with load-balanced routing, and an order service that proves load balancing by reporting which port handled each request.

"Let's walk through the six steps."

## 1:00 – 2:00 | Steps 1-2: Eureka Server

*(Switch tabs to `EurekaServerApplication.java`)*

"Steps 1 and 2 configure the Eureka Server. We add `@EnableEurekaServer` to the main class — one annotation is all it takes to turn a Spring Boot app into a service registry.

*(Switch tabs to `application.properties`)*

"In the properties file, we set the port to 8761, disable self-registration with `eureka.client.register-with-eureka=false`, and disable fetching the registry since this is the registry itself."

## 2:00 – 3:00 | Steps 3-4: API Gateway

*(Switch tabs to `ApiGatewayApplication.java`)*

"Steps 3 and 4 configure the API Gateway. We add `@EnableDiscoveryClient` to connect to Eureka.

*(Switch tabs to `application.yml`)*

"In the YAML configuration, we define a route. The `uri` is `lb://order-service` — the `lb` prefix tells Spring Cloud to look up 'order-service' in Eureka and load-balance across all registered instances. The predicate `Path=/api/orders/**` captures the requests, and the `StripPrefix=1` filter removes the `/api` prefix before forwarding."

## 3:00 – 4:00 | Steps 5-6: Order Service

*(Switch tabs to `OrderController.java`)*

"Steps 5 and 6 are in the Order Service. We inject `server.port` using `@Value` and include it in the response. This is how we prove load balancing — when we call the gateway multiple times, we will see the port alternate between instances.

"In Step 6, we log the `X-Correlation-ID` header if present. This demonstrates how the gateway can propagate tracking headers through to downstream services."

## 4:00 – 5:30 | Running and Verification

*(🖥️ Terminal 1: `cd eureka-server && mvn spring-boot:run`)*

*(🖥️ Terminal 2: `cd api-gateway && mvn spring-boot:run`)*

*(🖥️ Terminal 3: `cd order-service && mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081`)*

*(🖥️ Terminal 4: `cd order-service && mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8082`)*

"Let's start everything up. Eureka on 8761, gateway on 8080, and two order-service instances on ports 8081 and 8082.

*(🖥️ Browser: `http://localhost:8761`)*

"The Eureka dashboard shows two instances of 'ORDER-SERVICE' registered.

*(🖥️ Terminal: `curl -s http://localhost:8080/api/orders | jq`)*

"First call through the gateway — the response includes `port: 8081`.

*(🖥️ Terminal: `curl -s http://localhost:8080/api/orders | jq`)*

"Second call — `port: 8082`. Round-robin load balancing is working perfectly. The client hits the same gateway URL, and the requests are distributed across both instances."

## 5:30 – 6:00 | Outro

"To summarize: We configured Eureka for dynamic service discovery, wired the API Gateway with load-balanced routing, and proved round-robin distribution by including the serving port in responses. This is the production-ready pattern for routing traffic in a microservices architecture.

"Great job if you got this working. I'll see you in the next module."
