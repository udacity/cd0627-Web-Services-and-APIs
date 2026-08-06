# Demo Walkthrough: API Gateway and Service Discovery (Module 17)

**Focus:** From Hardcoded URLs to Dynamic Service Discovery with Eureka and Spring Cloud Gateway
**Target Length:** 5 - 7 minutes
**Files:** `EurekaServerApplication.java`, `ApiGatewayApplication.java`, `application.yml`

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing the project structure with three sub-projects)*

"Welcome back. In this demo, we are going to look at API Gateway and Service Discovery.

"In the last module, we had two microservices communicating over HTTP using hardcoded URLs — `http://localhost:8082`. But what happens when you deploy to production and have 5 instances of the inventory service across different servers? You cannot hardcode every address.

"The solution is two components: a Service Registry where services register themselves, and an API Gateway that routes client requests to the correct service using the registry. Let's see how Spring Cloud implements this."

## 1:00 – 2:30 | Eureka Server: The Service Registry

*(Switch tabs to `EurekaServerApplication.java`)*

"We start with the Eureka Server. The entire setup is one annotation: `@EnableEurekaServer`. This turns a plain Spring Boot application into a service registry.

*(Switch tabs to `eureka-server/application.properties`)*

"The configuration disables self-registration — this server itself does not need to register as a client. It runs on port 8761, the Eureka default.

"Once started, Eureka provides a web dashboard at `http://localhost:8761` where we can see every registered service."

## 2:30 – 4:00 | Spring Cloud Gateway: Intelligent Routing

*(Switch tabs to `ApiGatewayApplication.java`)*

"Next is the API Gateway. It has `@EnableDiscoveryClient`, which tells it to connect to Eureka and discover services dynamically.

*(Switch tabs to `api-gateway/application.yml`)*

"The routing configuration is where it gets interesting. We define a route with the ID 'order-service'. The `uri` is `lb://order-service` — `lb` stands for 'load-balanced'. Instead of hardcoding a URL, we tell the gateway: 'look up order-service in Eureka and distribute requests across all its instances.'

"The `predicates` section says: route any request matching `/api/orders/**` through this rule. The `filters` section strips the `/api` prefix before forwarding."

## 4:00 – 5:30 | Running and Load Balancing


"Let's see it in action. We need four terminals.

*(🖥️ Terminal 1: `cd eureka-server && mvn spring-boot:run`)*

"First, Eureka Server on port 8761.

*(🖥️ Terminal 2: `cd api-gateway && mvn spring-boot:run`)*

"Second, the API Gateway on port 8080.

*(🖥️ Terminal 3: `cd order-service && SERVER_PORT=8081 mvn spring-boot:run`)*

"Third, Order Service instance 1 on port 8081.

*(🖥️ Terminal 4: `cd order-service && SERVER_PORT=8082 mvn spring-boot:run`)*

"And Order Service instance 2 on port 8082.

*(🖥️ Terminal: `curl -s -H "Accept: application/json" http://localhost:8761/eureka/apps | jq '.applications.application[] | {name: .name, instances: [.instance[] | .hostName + ":" + (.port."$" // .port | tostring)]}'`)*

"Querying the Eureka REST API, we can see both order-service instances registered — one on 8081 and one on 8082.

*(🖥️ Terminal: `curl -s http://localhost:8080/api/orders`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/api/orders`)*

"Now let's hit the gateway. `curl http://localhost:8080/api/orders` — notice the port in the response. If we call it again, the port changes. The gateway is load-balancing requests across both instances using round-robin. The client hits one URL, and the gateway dynamically routes to the correct service."

## 5:30 – 6:00 | Outro & Summary

"To summarize: Eureka Server provides dynamic service registration and discovery. Spring Cloud Gateway routes client requests using `lb://` URIs and distributes load across all registered instances. The client never needs to know the actual IP addresses or ports of your services.

"Thanks for watching, and I'll see you in the next module."
