package com.ecommerce.product.controller;

import com.ecommerce.product.dto.CreateProductRequest;
import com.ecommerce.product.dto.PatchProductRequest;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * <h2>Module 3 Demo – Product API Controller</h2>
 *
 * <p>This controller is the centrepiece of the instructor demo.
 * It walks through three key REST contract concepts:
 *
 * <ol>
 *   <li><b>Resource Mapping</b> – how to map a domain entity to URIs.</li>
 *   <li><b>Correct Status Codes &amp; Headers</b> – 201 + Location instead of 200 on create.</li>
 *   <li><b>Partial Update via PATCH</b> – updating a single data field safely.</li>
 * </ol>
 *
 * <h3>Endpoints exposed by this controller</h3>
 * <pre>
 *  GET    /products        → list all products           (200 OK)
 *  GET    /products/{id}   → get one product             (200 OK | 404 Not Found)
 *  POST   /products        → create a new product        (201 Created + Location header)
 *  PATCH  /products/{id}   → partially update a product  (200 OK | 404 Not Found)
 * </pre>
 *
 * <h3>Demo talking points</h3>
 * <ul>
 *   <li>Why does POST return 201 and not 200?</li>
 *   <li>What does the {@code Location} header tell the client?</li>
 *   <li>Why PATCH and not PUT for a price-only change?</li>
 * </ul>
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // =========================================================================
    // STEP 1 – Resource Mapping
    // =========================================================================

    /**
     * <b>Demo Step 1a</b> – List all products.
     *
     * <p>Talk track:
     * "The collection resource is always the plural noun: {@code /products}.
     * No verb in the URI — REST uses HTTP methods as the verbs."
     *
     * <p>Status code: 200 OK (reading an existing resource that exists)
     */
    @GetMapping
    public List<Product> listProducts() {
        return repository.findAll();
    }

    /**
     * <b>Demo Step 1b</b> – Get a single product by ID.
     *
     * <p>Talk track:
     * "To address a specific item we add the identifier to the path:
     * {@code /products/1}. If that product doesn't exist, 404 is the
     * only honest answer — returning an empty 200 is misleading."
     *
     * <p>Status codes:
     * <ul>
     *   <li>200 OK – product found.</li>
     *   <li>404 Not Found – no product with that id.</li>
     * </ul>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)                           // 200 OK
                .orElse(ResponseEntity.notFound().build());        // 404 Not Found
    }

    // =========================================================================
    // STEP 2 – Status Codes & Headers
    // =========================================================================

    /**
     * <b>Demo Step 2</b> – Create a new product.
     *
     * <p>Talk track:
     * "Watch what happens when we return 200 OK for creation — the client has
     * no way to tell whether a new resource was made or we just returned an
     * existing one. The fix is two things:
     * <ol>
     *   <li>Status 201 Created — explicitly signals resource creation.</li>
     *   <li>Location header — tells the client exactly where to find it.</li>
     * </ol>
     * Every major framework (GitHub, Stripe, AWS) follows this pattern."
     *
     * <p>Request body (JSON):
     * <pre>{@code { "name": "...", "description": "...", "price": 99.99 }}</pre>
     *
     * <p>Response:
     * <pre>
     * HTTP/1.1 201 Created
     * Location: /products/4
     * Content-Type: application/json
     *
     * { "id": 4, "name": "...", ... }
     * </pre>
     *
     * @param request the product data from the request body
     * @param ucb     injected by Spring to build an absolute URI
     * @return 201 Created with Location header and the created product in the body
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest request,
            UriComponentsBuilder ucb) {

        Product created = repository.create(
                request.name(),
                request.description(),
                request.price()
        );

        // Build: /products/{id}  →  e.g. /products/4
        URI location = ucb.path("/products/{id}")
                .buildAndExpand(created.id())
                .toUri();

        // ★ Key teaching moment: 201 Created + Location header
        return ResponseEntity
                .created(location)   // sets HTTP 201 + Location header automatically
                .body(created);
    }

    // =========================================================================
    // STEP 3 – State Change via REST (PATCH)
    // =========================================================================

    /**
     * <b>Demo Step 3</b> – Partially update a product (e.g. change price only).
     *
     * <p>Talk track:
     * "PATCH is perfect here because changing a price is a pure data field update.
     * The client only sends the field(s) it wants to change; we merge the rest
     * from what we already have stored. There is no side-effect beyond the data
     * change, so standard REST is sufficient.
     *
     * <p>This contrasts with what you'll see in the exercise: cancelling an
     * order isn't just a field change — it triggers refunds, restocking,
     * notification emails… standard PATCH doesn't capture that intent safely."
     *
     * <p>Example request (price update only):
     * <pre>{@code
     * PATCH /products/1
     * Content-Type: application/json
     *
     * { "price": 129.99 }
     * }</pre>
     *
     * <p>Status codes:
     * <ul>
     *   <li>200 OK – product updated, updated product returned.</li>
     *   <li>404 Not Found – no product with that id.</li>
     * </ul>
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable long id,
            @RequestBody PatchProductRequest request) {

        return repository.patch(id, request.getName(), request.getDescription(), request.getPrice())
                .map(ResponseEntity::ok)                          // 200 OK
                .orElse(ResponseEntity.notFound().build());       // 404 Not Found
    }
}
