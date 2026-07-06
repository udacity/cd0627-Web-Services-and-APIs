# Module 2 - REST Controllers and Data Binding - Exercise Instructions

## Exercise Overview

You are a backend engineer at an e-commerce startup. The frontend team needs a new API to fetch order details. You must build out the REST controller to handle these requests and return the expected JSON.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Open `OrderController.java`.

### Step 2
Annotate the class with `@RestController`.

### Step 3
Implement `@GetMapping("/{id}")` and bind the ID using `@PathVariable`.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] HTTP GET `/orders/1` returns a 200 OK with JSON data.
- [ ] Spring boot starts successfully.
