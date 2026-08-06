# Demo Walkthrough: Asynchronous Messaging with Kafka (Module 29)

**Focus:** From Synchronous HTTP to Event-Driven Architecture with Kafka
**Target Length:** 5 - 7 minutes
**Files:** `DemoApplication.java`, `Producer.java`, `Consumer.java`, `KafkaLocalConfig.java`

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing the project open in the IDE)*

"Welcome back. In this demo, we are going to look at Asynchronous Messaging with Apache Kafka.

"In our microservices architecture, the Order Service calls the Inventory Service synchronously via HTTP. If the Inventory Service is slow or down, the Order Service blocks and the customer waits. This tight coupling is fragile.

"Kafka solves this by decoupling the services. The Order Service publishes an event to a topic, and the Inventory Service consumes it asynchronously. The services do not need to be online at the same time."

## 1:00 – 2:30 | The Embedded Kafka Broker

*(Switch tabs to `DemoApplication.java`)*

"First, the infrastructure. We use an embedded Kafka broker for development — no external Kafka installation needed. In `DemoApplication.main()`, we create an `EmbeddedKafkaKraftBroker` and start it before the Spring context boots. This is critical — the broker assigns a dynamic port, and we set it as a system property so Spring Kafka auto-configuration picks it up.

*(Switch tabs to `KafkaLocalConfig.java`)*

"In `KafkaLocalConfig`, we create a `NewTopic` bean using `TopicBuilder`. This ensures the topic exists when the application starts. In production, topics would be managed separately, but for development this is convenient."

## 2:30 – 3:30 | The Producer

*(Switch tabs to `Producer.java`)*

"The producer uses Spring's `KafkaTemplate` — the Kafka equivalent of `RestTemplate`. Sending a message is one line: `kafkaTemplate.send("product-views", event)`.

"We publish a `ProductViewedEvent` record. Notice the event has a `null` productId — this is intentional. We want to see what happens when a consumer receives invalid data."

## 3:30 – 5:00 | Running and Observing

*(🖥️ Terminal: `mvn spring-boot:run`)*

"Let's run the application and watch the logs.

"First, the embedded Kafka broker starts. Then our producer publishes the event. The consumer picks it up asynchronously.

*(Highlight the consumer error in the logs)*

"The consumer received the event with a null productId. In a real system, this would fail validation. With `@RetryableTopic`, Kafka Spring can retry the message, and after exhausting retries, route it to a dead-letter topic.

"This is the power of event-driven architecture — the producer does not care if the consumer succeeds or fails. The consumer handles retries independently."

## 5:00 – 5:30 | Outro & Summary

"To summarize: Kafka decouples producers and consumers, enabling asynchronous communication. `KafkaTemplate` sends messages, `@KafkaListener` receives them, and `@RetryableTopic` handles failures with retry and dead-letter routing. In the exercise, you will build a complete event pipeline with retry, backoff, and error classification.

"Thanks for watching, and I'll see you in the next module."
