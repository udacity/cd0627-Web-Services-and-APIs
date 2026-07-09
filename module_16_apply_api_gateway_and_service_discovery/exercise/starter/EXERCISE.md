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

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Services register themselves with Eureka.
- [ ] The Gateway routes `/api/orders` to the correct service.
- [ ] Correlation IDs are injected into the headers.
