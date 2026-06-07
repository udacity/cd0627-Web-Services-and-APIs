package com.ecommerce.rma.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TODO (Step 1 – Data Transfer Objects):
 * This record represents the JSON body returned to the caller after the AI has
 * evaluated a return request.
 *
 * <p><b>Your task:</b> The {@code approved} and {@code reason} fields are already
 * defined. In {@code RmaService} (Steps 3–5) you will construct this object based
 * on what the AI decides after consulting the return policy.
 *
 * <p>Example JSON response:
 * <pre>{@code
 * {
 *   "approved": true,
 *   "reason": "Defective electronics are eligible for a full refund within 30 days."
 * }
 * }</pre>
 *
 * @param approved {@code true} if the AI determined the return should be approved,
 *                 {@code false} otherwise.
 * @param reason   Human-readable explanation produced by the AI explaining the
 *                 approval or rejection decision. This is what the customer sees.
 */
@Schema(description = "Decision returned to the caller after AI policy evaluation")
public record ReturnResponse(

        @Schema(description = "Whether the return was approved", example = "true")
        boolean approved,

        @Schema(description = "AI-generated explanation for the decision",
                example = "Defective electronics are eligible within 30 days.")
        String reason

) {}