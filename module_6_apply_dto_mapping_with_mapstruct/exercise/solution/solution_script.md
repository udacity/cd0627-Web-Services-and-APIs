# Solution Walkthrough: DTOs with MapStruct (Module 6)

**Focus:** The Order API — Hiding Internal Fields and Validating Input
**Target Length:** 5 - 7 minutes
**Files:** `OrderResponse.java`, `CreateOrderRequest.java`, `OrderMapper.java`, `OrderController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `Order.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the DTOs with MapStruct exercise.

"Our starting point is this `Order` entity. Notice it has seven fields, including two that are sensitive: `internalMargin` — our profit data — and `auditTimestamp` — an internal tracking field. If we return this entity directly from our API, both of those leak to the client.

"Our goal was to create DTOs that expose only the safe fields, add validation to incoming requests, and wire up MapStruct to handle the conversion automatically."

## 1:00 – 2:00 | Step 1: The Response DTO

*(Switch tabs to `OrderResponse.java`)*

"Step 1 is the response DTO. We define `OrderResponse` as a Java record with exactly three fields: `id`, `totalAmount`, and `status`.

"Notice what is absent — `internalMargin` and `auditTimestamp` are not in this record. When MapStruct maps from the `Order` entity to this record, those sensitive fields are simply ignored. The client never sees them."

## 2:00 – 3:30 | Step 2 & 3: The Request DTO with Validation

*(Switch tabs to `CreateOrderRequest.java`)*

"Steps 2 and 3 are the request DTO with validation. `CreateOrderRequest` is a Java record with four fields: `totalAmount`, `status`, `deliveryDate`, and `itemIds`.

"Look at the validation annotations. `@Positive` on `totalAmount` ensures the amount is greater than zero. `@NotBlank` on `status` rejects empty strings. `@FutureOrPresent` on `deliveryDate` ensures we cannot schedule a delivery in the past. And `@NotEmpty` on `itemIds` guarantees the list has at least one item.

"These annotations work with Spring's `@Valid` annotation on the controller. If any constraint is violated, Spring returns a 400 Bad Request automatically — before our business logic even executes."

## 3:30 – 4:30 | Step 4: The MapStruct Mapper

*(Switch tabs to `OrderMapper.java`)*

"Step 4 is the MapStruct mapper. `OrderMapper` is an interface annotated with `@Mapper(componentModel = "spring")`. We also set `unmappedTargetPolicy = ReportingPolicy.IGNORE` — this tells MapStruct to silently skip any fields that exist on the target but not on the source, rather than generating a compile-time warning.

"The interface declares two methods: `toResponse()` converts from `Order` to `OrderResponse`, and `toEntity()` converts from `CreateOrderRequest` to `Order`. MapStruct generates the implementation at compile time — no reflection, no runtime overhead."

## 4:30 – 5:30 | Step 5: The Refactored Controller

*(Switch tabs to `OrderController.java`)*

"Step 5 is putting it all together in the controller.

*(Highlight the `createOrder` method)*

"In `POST /orders`, we accept `@Valid @RequestBody CreateOrderRequest`. Validation runs first. Then we call `mapper.toEntity(request)` to convert the DTO into our internal `Order` entity.

*(Highlight the `getOrder` method)*

"In `GET /orders/{id}`, we load the entity — and notice that the entity has `internalMargin` set to thirty dollars and an `auditTimestamp`. But we return `mapper.toResponse(order)`, which maps only `id`, `totalAmount`, and `status`. The sensitive fields are gone."

## 5:30 – 6:30 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/orders/1 | jq`)*

"Let's verify. `GET /orders/1` returns only `id`, `totalAmount`, and `status`. No `internalMargin`, no `auditTimestamp`. Our data leak is plugged.

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{"totalAmount":-5,"status":"","deliveryDate":"2020-01-01T00:00:00Z","itemIds":[]}' | jq`)*

"And if we send an invalid request — negative amount, blank status, past delivery date, empty items list — we get a 400 Bad Request with specific validation error messages for each field."

## 6:30 – 7:00 | Outro

"To summarize: We created response and request DTOs using Java records, added Bean Validation annotations, and used MapStruct to generate the mapping code. The result is a clean, safe API contract where internal fields never leak and invalid input is rejected before it reaches the business logic.

"Great job if you got this working. I'll see you in the next module."
