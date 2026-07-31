# Solution Walkthrough: Asynchronous Messaging with Kafka (Module 28)

**Focus:** Retry, Dead-Letter Topics, and Error Classification
**Target Length:** 5 - 7 minutes
**Files:** `InventoryConsumer.java`, `KafkaLocalConfig.java`, `OrderService.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the project open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Kafka Messaging exercise.

"Our goal was to build an event-driven order pipeline where the Order Service publishes events and two consumers — Inventory and Notification — process them independently. The key challenge was implementing retry with exponential backoff and routing non-retryable errors to a dead-letter topic."

## 1:00 – 2:30 | Steps 1-3: The Retry-Enabled Consumer

*(Switch tabs to `InventoryConsumer.java`)*

"The `InventoryConsumer` uses `@KafkaListener(topics = "order-events", groupId = "inventory-group")` to subscribe to events.

"Step 1 adds `@RetryableTopic` with `attempts = "3"` — retry up to 3 times. Step 2 would add `@Backoff` for exponential backoff if configured.

"Step 3 is the most important: `exclude = MalformedOrderException.class`. This tells Spring that `MalformedOrderException` is a business error — not a transient failure — so it should never be retried. It goes straight to the dead-letter topic.

"The consumer logic has two error paths. If the payload is null, it throws `MalformedOrderException` — no retries, straight to DLT. If the orderId is 'ORD-LOCK', it throws a `TransientDataAccessException` — a database lock that is worth retrying."

## 2:30 – 3:30 | Step 4: Kafka Configuration

*(Switch tabs to `KafkaLocalConfig.java`)*

"Step 4 sets up the embedded Kafka broker. `EmbeddedKafkaKraftBroker` starts an in-process Kafka on port 9092. `NewTopic` creates the `orders-topic` with 1 partition and 1 replica.

"For development, this eliminates the need for a separate Kafka installation. The broker starts with the application and shuts down when it stops."

## 3:30 – 5:00 | The Producer and Multiple Consumer Groups

*(Switch tabs to `OrderService.java`)*

"The `OrderService` publishes three events: a good event, a malformed event with null payload, and a transient error event.

*(Switch tabs to `NotificationConsumer.java`)*

"Notice we have two consumers with different `groupId`s — `inventory-group` and `notification-group`. Both receive every event independently. This is Kafka's power — one event can trigger multiple downstream actions. The inventory consumer updates stock, while the notification consumer sends emails.

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(Watch the console output)*

"Let's run it and watch the logs. The good event processes successfully in both consumers. The malformed event — `MalformedOrderException` — goes straight to the dead-letter topic with zero retries. The transient error event retries up to 3 times before being routed to the DLT."

## 5:00 – 5:30 | Outro

"To summarize: We built an event-driven pipeline with Kafka. `@RetryableTopic` provides automatic retry with dead-letter routing. The `exclude` parameter prevents business errors from wasting retry attempts. And multiple consumer groups allow independent processing of the same events.

"Great job if you got this working. I'll see you in the next module."
