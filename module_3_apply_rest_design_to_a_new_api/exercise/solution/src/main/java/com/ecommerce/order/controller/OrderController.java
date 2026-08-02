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
 * <h2>Module 3 Exercise – Order API Controller (SOLUTION)</h2>
 *
 * <p>This is the complete, working implementation of the Order REST contract.
 * Three endpoints are implemented:
 *
 * <pre>
 *  GET   /orders/{id}         → fetch a single order         (200 OK | 404 Not Found)
 *  GET   /orders/{id}/items   → fetch items for an order     (200 OK | 404 Not Found)
 *  POST  /orders/{id}/cancel  → cancel an order idempotently (200 OK | 404 Not Found)
 * </pre>
 *
 * <h3>Key design decisions</h3>
 * <ol>
 *   <li><b>GET /orders/{id}</b> – returns 404, not an empty 200, when an order does not
 *       exist. An empty 200 would be a lie; 404 is the honest, unambiguous answer.</li>
 *   <li><b>GET /orders/{id}/items</b> – the nested path expresses ownership in the URI.
 *       If the parent order does not exist, 404 is returned. If it exists with no items,
 *       200 with an empty array is correct (empty list is a valid state).</li>
 *   <li><b>POST /orders/{id}/cancel</b> – uses POST, not PATCH, because cancellation is
 *       a business *action* that triggers side-effects (refund, restock, email). PATCH
 *       implies a data-field update; it does not communicate complex business intent.
 *       The named sub-resource action path documents intent explicitly and lets the
 *       service enforce invariants (e.g., can't cancel a shipped order).
 *       The implementation is *idempotent at the application level*: if the order is
 *       already CANCELLED, we return 200 immediately without re-triggering any
 *       side-effects — preventing double-refunds.</li>
 * </ol>
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    // =========================================================================
    // Endpoint 1 – GET /orders/{id}
    // =========================================================================

    /**
     * Fetches a single order by its ID.
     *
     * <p>Status codes:
     * <ul>
     *   <li><b>200 OK</b> – order found, full order object returned.</li>
     *   <li><b>404 Not Found</b> – no order with that id exists.</li>
     * </ul>
     *
     * @param id the order id from the URL path
     * @return 200 OK with the {@link Order}, or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)              // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    // =========================================================================
    // Endpoint 2 – GET /orders/{id}/items
    // =========================================================================

    /**
     * Returns all line items belonging to a specific order.
     *
     * <p>Items are a nested sub-collection of an order, expressed as
     * {@code /orders/{id}/items}. This makes the ownership relationship
     * explicit in the URI and simplifies authorization scoping.
     *
     * <p>Status codes:
     * <ul>
     *   <li><b>200 OK</b> – order found; returns the item list (may be empty).</li>
     *   <li><b>404 Not Found</b> – no order with that id exists.</li>
     * </ul>
     *
     * @param id the order id from the URL path
     * @return 200 OK with the list of {@link OrderItem}s, or 404 Not Found
     */
    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable long id) {
        return repository.findById(id)
                .map(order -> ResponseEntity.ok(order.getItems())) // 200 OK, list may be []
                .orElse(ResponseEntity.notFound().build());        // 404 Not Found
    }

    // =========================================================================
    // Endpoint 3 – POST /orders/{id}/cancel
    // =========================================================================

    /**
     * Cancels an order.
     *
     * <p><b>Why POST, not PATCH?</b><br>
     * {@code PATCH /orders/{id}} with {@code {"status":"CANCELLED"}} is a
     * generic data-field update. It does not communicate that cancellation
     * triggers refunds, inventory restocking, and notification emails.
     * A named action endpoint ({@code POST /orders/{id}/cancel}) expresses
     * business intent explicitly, allows the implementation to enforce
     * business rules (e.g., cannot cancel an already-shipped order), and
     * is the pragmatic "RPC-flavoured REST" pattern used by Stripe, GitHub,
     * and many other production APIs.
     *
     * <p><b>Idempotency</b><br>
     * If the order is already {@link OrderStatus#CANCELLED}, this method
     * returns 200 OK immediately with an "already cancelled" message.
     * No side-effects are re-triggered — preventing double-refunds.
     * This makes the endpoint safe to call multiple times.
     *
     * <p>Status codes:
     * <ul>
     *   <li><b>200 OK</b> – either newly cancelled, or already cancelled.</li>
     *   <li><b>404 Not Found</b> – no order with that id exists.</li>
     * </ul>
     *
     * @param id the order id from the URL path
     * @return 200 OK with a {@link CancelOrderResponse}, or 404 Not Found
     */
    @PostMapping("/{id}/cancel")
    public ResponseEntity<CancelOrderResponse> cancelOrder(@PathVariable long id) {
        return repository.findById(id)
                .map(order -> {

                    // Idempotency guard: already cancelled — do nothing, return 200.
                    if (order.getStatus() == OrderStatus.CANCELLED) {
                        return ResponseEntity.ok(new CancelOrderResponse(
                                order.getId(),
                                "CANCELLED",
                                "Order " + order.getId() + " was already cancelled. No further action taken."
                        ));
                    }

                    // First cancellation: run business logic.
                    order.cancel();                          // state transition
                    simulateRefund(order.getId());           // ① refund payment
                    simulateRestock(order.getItems());       // ② restock inventory
                    simulateSendEmail(order.getCustomerId());// ③ notify customer

                    return ResponseEntity.ok(new CancelOrderResponse(
                            order.getId(),
                            "CANCELLED",
                            "Order " + order.getId() + " successfully cancelled. Refund initiated."
                    ));
                })
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    // -------------------------------------------------------------------------
    // Simulated side-effect methods (log only – no real external calls)
    // -------------------------------------------------------------------------

    private void simulateRefund(long orderId) {
        System.out.println("[RefundService] Initiating refund for order " + orderId);
    }

    private void simulateRestock(List<OrderItem> items) {
        items.forEach(item ->
                System.out.println("[InventoryService] Restocking " + item.quantity()
                        + "x '" + item.name() + "' (productId=" + item.productId() + ")")
        );
    }

    private void simulateSendEmail(String customerId) {
        System.out.println("[EmailService] Sending cancellation confirmation to " + customerId);
    }
}
