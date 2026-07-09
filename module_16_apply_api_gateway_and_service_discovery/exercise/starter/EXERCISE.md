# Module 16 - API Gateway and Service Discovery - Exercise Instructions

## Exercise Overview

With multiple microservices running, clients don't know which IP addresses to call. You must configure an API Gateway to route requests and a Eureka Service Registry for dynamic service discovery.

---

## Prerequisites
- **Java 23+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | Configure the `eureka-server` to act as the service registry. |
| 2 | Configure `api-gateway` routes using Spring Cloud Gateway. |
| 3 | Implement a `CorrelationIdFilter` in the gateway to attach tracking headers to all requests. |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

To demonstrate Service Discovery and Load Balancing, you must run multiple applications in separate terminal windows:

1. **Start Eureka Server**:
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```
2. **Start API Gateway**:
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```
3. **Start Order Service (Instance 1 on port 8081)**:
   ```bash
   cd order-service
   SERVER_PORT=8081 mvn spring-boot:run
   ```
4. **Start Order Service (Instance 2 on port 8082)**:
   ```bash
   cd order-service
   SERVER_PORT=8082 mvn spring-boot:run
   ```

**Test Load Balancing**:
Hit the gateway multiple times and observe the logs in the two `order-service` terminals to see the requests distributed round-robin!
```bash
curl http://localhost:8080/api/orders
```