# Module 6 - Spring Security - Exercise Instructions

## Exercise Overview

Security audit alert! Your APIs are completely open to the public. You must lock down the endpoints using Spring Security and ensure only users with the 'ADMIN' role can delete orders.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Create a `SecurityConfig` class with `@EnableWebSecurity`.

### Step 2
Define a `SecurityFilterChain` bean to require authentication for `/api/**`.

### Step 3
Add `@PreAuthorize("hasRole('ADMIN')")` to the delete endpoint.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Unauthenticated requests return 401 Unauthorized.
- [ ] Non-admin requests to delete return 403 Forbidden.
