# Module 32 - API Documentation and Testing - Exercise Instructions

## Exercise Overview

Frontend developers are complaining that they don't know how to use your API. You need to auto-generate an interactive Swagger UI documentation dashboard directly from your code.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | Add the `springdoc-openapi-starter-webmvc-ui` dependency. |
| 2 | In `src/main/java/com/ecommerce/docs/OrderController.java`, annotate your controller endpoints with `@Operation` and `@ApiResponses`. |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The Swagger UI loads successfully at `/swagger-ui.html`.
- [ ] The custom descriptions are visible in the UI.
