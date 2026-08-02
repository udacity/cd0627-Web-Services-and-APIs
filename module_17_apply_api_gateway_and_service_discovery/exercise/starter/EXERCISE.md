# Module 17 — API Gateway and Service Discovery — Exercise Instructions

## Exercise Overview

With multiple microservices running, clients don't know which IP addresses to call. You must configure an API Gateway to route requests and a Eureka Service Registry for dynamic service discovery.

---

## Prerequisites
- **Java 23+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Eureka Server (`eureka-server/`)

1. In `EurekaServerApplication.java`, add `@EnableEurekaServer` to enable the service registry.

2. In `application.properties`, configure port **8761** and disable self-registration (since this is the server itself).

### API Gateway (`api-gateway/`)

3. In `ApiGatewayApplication.java`, add `@EnableDiscoveryClient` to register with Eureka.

4. In `application.yml`, configure gateway routes to forward `/api/orders/**` to `lb://order-service` (load-balanced via Eureka).

### Order Service (`order-service/`)

5. Inject `server.port` using `@Value` to prove load balancing — include the port in responses so you can see which instance handled the request.

6. Log the incoming `X-Correlation-ID` header if present — this demonstrates how headers propagate through the gateway.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the TODO comments in the starter code!

---

## Running the Exercise

Start services in **separate terminals** in this order:

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
Hit the gateway multiple times and observe the logs in the two `order-service` terminals to see requests distributed round-robin:
```bash
curl http://localhost:8080/api/orders
curl http://localhost:8080/api/orders
```

---

## Success Criteria

- [ ] Eureka dashboard is accessible at `http://localhost:8761`.
- [ ] API Gateway routes `/api/orders/**` to the order-service via Eureka.
- [ ] Load balancing distributes requests across multiple order-service instances.
- [ ] The response includes the serving port to verify round-robin.