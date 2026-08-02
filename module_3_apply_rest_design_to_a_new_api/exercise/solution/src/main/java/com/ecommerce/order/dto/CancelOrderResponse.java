package com.ecommerce.order.dto;

/**
 * Response body returned after a successful order cancellation.
 *
 * <p>Example JSON (first cancellation):
 * <pre>{@code
 * {
 *   "orderId": 1,
 *   "status": "CANCELLED",
 *   "message": "Order 1 successfully cancelled. Refund initiated."
 * }
 * }</pre>
 *
 * <p>Example JSON (idempotent – already cancelled):
 * <pre>{@code
 * {
 *   "orderId": 1,
 *   "status": "CANCELLED",
 *   "message": "Order 1 was already cancelled. No further action taken."
 * }
 * }</pre>
 */
public record CancelOrderResponse(
        long orderId,
        String status,
        String message
) {}
