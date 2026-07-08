# Module 14 - Spring Caching - Exercise Instructions

## Exercise Overview

Your product catalog API is slow because it queries the database on every request. You need to implement application-level caching to store frequently accessed data in memory.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Add `@EnableCaching` to the main application class.

### Step 2
Annotate the read method with `@Cacheable("products")`.

### Step 3
Annotate the update/delete methods with `@CacheEvict` to prevent stale data.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Subsequent requests to the same endpoint are significantly faster.
- [ ] Logs show the database is only queried on the first request.
