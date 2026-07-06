# Module 4 - Spring Data JPA - Exercise Instructions

## Exercise Overview

Your startup is migrating away from hardcoded in-memory lists. You need to connect the application to a relational database using Spring Data JPA so that orders are persisted properly.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Open `Order.java` and add `@Entity` and `@Id`.

### Step 2
Create `OrderRepository.java` extending `CrudRepository`.

### Step 3
Inject the repository into your service layer to perform CRUD operations.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Application creates the H2 schema on startup.
- [ ] Saving an order persists it to the database.
