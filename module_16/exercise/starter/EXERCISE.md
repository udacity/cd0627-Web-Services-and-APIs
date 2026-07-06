# Module 16 - Testing Slices - Exercise Instructions

## Exercise Overview

You've built the API, but you have zero test coverage! You must write unit and integration tests to ensure the controllers and repositories work exactly as expected.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Write a `@WebMvcTest` for the controller, mocking the service layer.

### Step 2
Use `MockMvc` to perform a GET request and assert the JSON path.

### Step 3
Write a `@DataJpaTest` to verify custom repository queries against an embedded database.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] All tests pass using `mvn test`.
- [ ] The WebMvcTest successfully isolates the web layer.
