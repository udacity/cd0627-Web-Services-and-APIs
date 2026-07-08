package com.ecommerce.order.dto;

/**
 * Response body returned after a successful order cancellation.
 *
 * <p>Fields:
 * <ul>
 *   <li>{@code orderId} – The id of the cancelled order.</li>
 *   <li>{@code status}  – Always "CANCELLED" on success.</li>
 *   <li>{@code message} – Human-readable confirmation (or "already cancelled" note).</li>
 * </ul>
 *
 * <p>Example JSON:
 * <pre>{@code
 * {
 *   "orderId": 42,
 *   "status": "CANCELLED",
 *   "message": "Order 42 successfully cancelled. Refund initiated."
 * }
 * }</pre>
 */
public record CancelOrderResponse(
        long orderId,
        String status,
        String message
) {}
