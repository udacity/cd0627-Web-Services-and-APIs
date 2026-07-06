# Module 18 - Pagination and Sorting - Exercise Instructions

## Exercise Overview

The `/orders` endpoint is crashing the server because it tries to return 1,000,000 records at once. You must implement pagination to return data in manageable chunks.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Update the repository method to accept a `Pageable` argument.

### Step 2
Modify the controller to accept a `Pageable` parameter.

### Step 3
Return a `Page<Order>` instead of a `List<Order>`.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Hitting `/orders?page=0&size=10` returns exactly 10 records.
- [ ] The response includes metadata like `totalElements`.
