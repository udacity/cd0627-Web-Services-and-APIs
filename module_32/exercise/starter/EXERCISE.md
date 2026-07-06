# Module 32 - Auto-Generated API Docs - Exercise Instructions

## Exercise Overview

Frontend developers are complaining that they don't know how to use your API. You need to auto-generate an interactive Swagger UI documentation dashboard directly from your code.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Add the `springdoc-openapi-starter-webmvc-ui` dependency.

### Step 2
Annotate your controller endpoints with `@Operation` and `@ApiResponses`.

### Step 3
Navigate to `/swagger-ui.html` in your browser to view the docs.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The Swagger UI loads successfully.
- [ ] The custom descriptions provided in `@Operation` are visible in the UI.
