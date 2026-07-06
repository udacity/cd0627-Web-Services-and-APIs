# Module 12 - Jakarta Validation - Exercise Instructions

## Exercise Overview

Bad data is polluting your database. Users are submitting negative prices and blank names. You need to enforce data integrity using Jakarta Validation annotations.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Add `@NotBlank` and `@Positive` to the fields in your Request DTO.

### Step 2
Add `@Valid` to the `@RequestBody` parameter in your controller.

### Step 3
Verify that invalid payloads are rejected automatically.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Submitting a blank name returns 400 Bad Request.
- [ ] The response details which field failed validation.
