# Module 2 Demo – Designing the Product API Contract

> **Audience:** Instructor / Teaching Assistant  
> **Duration:** 5–7 minutes  
> **Topic:** REST resource mapping, HTTP status codes, PATCH vs POST

---

## Prerequisites

| Requirement | Command to verify |
|---|---|
| Java 21+ | `java --version` |
| Maven wrapper | included in this folder |

---

## 1. Start the Application

```bash
# From the module_2/demo directory
./mvnw spring-boot:run
```

Expected output:
```
Started ProductApiDemoApplication on port 8080
```

> Three products are seeded automatically: Wireless Headphones (id=1),  
> Mechanical Keyboard (id=2), and USB-C Hub (id=3).

---

## Step 1 – Resource Mapping (2 min)

**Talking point:**
> "Before we write a single line of Java, we write the contract.  
> A resource is a *noun*, never a verb. HTTP methods are the verbs."

### 1a. List all products — `GET /products`

```bash
curl -s http://localhost:8080/products | python3 -m json.tool
```

Expected: **200 OK** + JSON array of 3 products.

**Point out:** plural noun in the URI, no verb, no `/getAllProducts`.

### 1b. Fetch a single product — `GET /products/{id}`

```bash
curl -i http://localhost:8080/products/1
```

Expected: **200 OK** + the Wireless Headphones object.

Then show the 404 path:

```bash
curl -i http://localhost:8080/products/999
```

Expected: **404 Not Found** (no body by default).

**Point out:** returning an empty 200 here would be a lie — 404 is the only
honest answer when a resource does not exist.

---

## Step 2 – Status Codes & Headers (2 min)

**Talking point:**
> "Watch what happens if we return 200 for a creation event —  
> the client has no signal that a *new* resource was created.  
> The fix is 201 Created *plus* a Location header."

### Create a product — `POST /products`

```bash
curl -i -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Ergonomic Mouse",
        "description": "Vertical grip, 6 programmable buttons",
        "price": 59.99
      }'
```

Expected response headers:
```
HTTP/1.1 201 Created
Location: /products/4
Content-Type: application/json
```

**Key teaching moments:**
1. **201 vs 200** — 201 tells every intermediary (CDN, proxy, client) that a resource was born.
2. **Location header** — the client can bookmark this URI and use it immediately.  
   No second round-trip needed to ask "where is my new resource?"

### Try fetching the new product immediately

```bash
curl -i http://localhost:8080/products/4
```

Expected: **200 OK** — the Location header told us exactly where to look.

---

## Step 3 – State Change via REST / PATCH (2 min)

**Talking point:**
> "PATCH is ideal when you need to change one or two data fields without  
> resending the whole object. Notice we send only `price` — everything  
> else is preserved on the server."

### Update a product price — `PATCH /products/{id}`

```bash
curl -i -X PATCH http://localhost:8080/products/1 \
  -H "Content-Type: application/json" \
  -d '{ "price": 129.99 }'
```

Expected: **200 OK** + the updated product. Verify that `name` and `description`
are unchanged while `price` is now 129.99.

```bash
curl -s http://localhost:8080/products/1 | python3 -m json.tool
```

**Point out:** because "price" is a pure data field, standard RESTful PATCH works perfectly.  
There are no side-effects — nothing triggers a refund, restocking, or notification email.

---

## Transition to Exercise

> "Standard data-field changes are easy — PATCH handles them cleanly.  
> But what happens when a business process requires a *meaningful action*,  
> like cancelling an order?  
> Cancellation isn't just setting `status = CANCELLED`. It triggers refunds,  
> restocks inventory, and fires notification emails. That's your task:  
> design the complete Order API contract."

---

## Summary of What Students Should Take Away

| Concept | What we demonstrated |
|---|---|
| Resource mapping | Nouns in the URI (`/products`, `/products/{id}`) |
| Collection vs. item | `GET /products` vs `GET /products/1` |
| 201 Created | Correct code for a POST that creates a resource |
| Location header | Client gets the URI of the new resource immediately |
| 404 Not Found | Honest answer when a resource doesn't exist |
| PATCH | Partial update of data fields (safe for simple changes) |
