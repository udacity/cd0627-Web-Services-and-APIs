# Module 30 - CQRS with Spring Events - Exercise Instructions

## Exercise Overview

Your monolithic service is doing too much. You need to decouple the write operations (Command) from the read operations (Query) using Spring Application Events.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Publish an event using `ApplicationEventPublisher` when a write occurs.

### Step 2
Create a separate read service with an `@EventListener` method.

### Step 3
Update a read-optimized data structure when the event is received.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Writing an order asynchronously triggers the listener.
- [ ] The read service maintains an eventually-consistent view of the data.
