package com.ecommerce.rma.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TODO (Step 1 – Data Transfer Objects):
 * This record represents the JSON body that a customer (or front-end) sends when
 * they want to initiate a return.
 *
 * <p>A Java {@code record} is an immutable data carrier. Spring's Jackson library
 * automatically maps the incoming JSON fields to the record components by name.
 *
 * <p><b>Your task:</b> Review these fields and make sure you understand them —
 * you will reference {@code customerId} and {@code complaintText} in
 * {@code RmaService} when building AI prompts and publishing Kafka events.
 *
 * <p>Example JSON payload:
 * <pre>{@code
 * {
 *   "customerId": "CUST-001",
 *   "complaintText": "The zipper on my jacket broke after two uses. I want a refund."
 * }
 * }</pre>
 *
 * @param customerId    Unique identifier of the customer making the return request.
 * @param complaintText Free-text description of the issue provided by the customer.
 *                      This is the text that will be analyzed by the AI in Step 3.
 */
@Schema(description = "Incoming return request payload from the customer")
public record ReturnRequest(

        @Schema(description = "Unique customer identifier", example = "CUST-001")
        String customerId,

        @Schema(description = "Customer's free-text description of the defect or issue",
                example = "The zipper broke after two uses.")
        String complaintText

) {}