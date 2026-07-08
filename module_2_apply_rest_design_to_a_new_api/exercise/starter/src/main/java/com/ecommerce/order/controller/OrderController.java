package com.ecommerce.order.controller;

import com.ecommerce.order.dto.CancelOrderResponse;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.OrderStatus;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h2>Module 2 Exercise – Order API Controller</h2>
 *
 * <p>Your task: implement three REST endpoints for the Order domain.
 * The method stubs are already wired up — you need to fill in the body
 * of each method and choose the correct Spring MVC annotations.
 *
 * <h3>Endpoints to implement</h3>
 * <pre>
 *  GET   /orders/{id}         → fetch a single order         (200 OK | 404 Not Found)
 *  GET   /orders/{id}/items   → fetch items for an order     (200 OK | 404 Not Found)
 *  POST  /orders/{id}/cancel  → cancel an order              (200 OK | 404 Not Found)
 * </pre>
 *
 * <h3>Hints</h3>
 * <ul>
 *   <li>Use {@code repository.findById(id)} to look up orders.</li>
 *   <li>Return {@code ResponseEntity.notFound().build()} for missing resources.</li>
 *   <li>For the cancel endpoint, check {@link OrderStatus} before processing —
 *       if the order is already CANCELLED, return 200 immediately (idempotency).</li>
 * </ul>
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    // =========================================================================
    // TODO (Step 1) 1 – GET /orders/{id}
    //
    // Add the correct @GetMapping annotation with the path variable.
    // Look up the order by id. If found, return it with 200 OK.
    // If not found, return 404 Not Found.
    //
    // Expected: GET /orders/1  →  200 OK  (order JSON)
    //           GET /orders/99 →  404 Not Found
    // =========================================================================

    /**
     * Fetches a single order by its ID.
     *
     * @param id the order id (path variable)
     * @return 200 OK with the Order, or 404 Not Found
     */
    public ResponseEntity<Order> getOrder(@PathVariable long id) {
        // TODO (Step 2): implement this method
        throw new UnsupportedOperationException("TODO: implement getOrder");
    }

    // =========================================================================
    // TODO (Step 3) 2 – GET /orders/{id}/items
    //
    // Fetch the items for a given order.
    // The items are a nested collection owned by the order.
    //
    // Rules:
    //   - If the order does not exist → 404 Not Found
    //   - If the order exists but has no items → 200 OK with empty list []
    //   - If the order exists and has items → 200 OK with the list
    //
    // Expected: GET /orders/1/items  →  200 OK  (array of OrderItem)
    //           GET /orders/99/items →  404 Not Found
    // =========================================================================

    /**
     * Returns all items belonging to a specific order.
     *
     * @param id the order id (path variable)
     * @return 200 OK with the item list, or 404 Not Found if the order doesn't exist
     */
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable long id) {
        // TODO (Step 4): implement this method
        throw new UnsupportedOperationException("TODO: implement getOrderItems");
    }

    // =========================================================================
    // TODO (Step 5) 3 – POST /orders/{id}/cancel
    //
    // Cancel an order. This is NOT a simple data-field update — it represents
    // a business action that in production would trigger:
    //   - A refund to the payment provider
    //   - Inventory restock for each item
    //   - A cancellation confirmation email
    //
    // Rules (idempotency):
    //   - If the order does not exist → 404 Not Found
    //   - If the order is ALREADY CANCELLED → 200 OK, return a message
    //     saying it was already cancelled (do NOT process a second refund!)
    //   - If the order is ACTIVE → cancel it, return 200 OK with a
    //     CancelOrderResponse confirming the cancellation
    //
    // Expected: POST /orders/1/cancel  →  200 OK  (CancelOrderResponse JSON)
    //           POST /orders/1/cancel  →  200 OK  ("already cancelled" message)
    //           POST /orders/99/cancel →  404 Not Found
    //
    // QUESTION: Why POST and not PATCH here?
    // Write your answer as a comment below before submitting.
    // =========================================================================

    /**
     * Cancels an order. Safe to call multiple times — will not double-refund.
     *
     * @param id the order id (path variable)
     * @return 200 OK with a {@link CancelOrderResponse}, or 404 Not Found
     */
    public ResponseEntity<CancelOrderResponse> cancelOrder(@PathVariable long id) {
        // TODO (Step 6): implement this method
        throw new UnsupportedOperationException("TODO: implement cancelOrder");
    }
}
