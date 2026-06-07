package com.ecommerce.rma.dto;

/**
 * TODO (Step 3 – Structured AI Output):
 * This record is the <b>target type</b> for Spring AI's {@code BeanOutputConverter}.
 * The AI will parse a customer's free-text complaint and populate this record
 * with structured, machine-readable fields.
 *
 * <p><b>How BeanOutputConverter works:</b>
 * When you wrap your {@code ChatClient} call with a converter like:
 * <pre>{@code
 * BeanOutputConverter<ReturnAnalysis> converter = new BeanOutputConverter<>(ReturnAnalysis.class);
 * }</pre>
 * Spring AI automatically appends a JSON schema instruction to your prompt so the
 * LLM returns a JSON object that matches these exact field names and types.
 * You then call {@code converter.convert(rawAiText)} to get a typed {@code ReturnAnalysis}.
 *
 * <p><b>Your task in RmaService Step 3:</b>
 * Build a prompt like:
 * <pre>{@code
 * "Analyse the following customer complaint and return a structured JSON response.\n"
 * + converter.getFormat()   // <-- this appends the JSON schema instruction
 * + "\nComplaint: " + request.complaintText()
 * }</pre>
 * Then parse the response with {@code converter.convert(response)}.
 *
 * @param sentiment   The emotional tone of the complaint: {@code "POSITIVE"},
 *                    {@code "NEUTRAL"}, or {@code "NEGATIVE"}.
 * @param isDefective {@code true} if the customer describes a hardware or
 *                    manufacturing defect; {@code false} for change-of-mind returns.
 * @param itemType    A short category string for the item, e.g. {@code "electronics"},
 *                    {@code "clothing"}, {@code "furniture"}. Used by the policy
 *                    advisor in Step 4 to look up the correct return window.
 */
public record ReturnAnalysis(

        String sentiment,
        boolean isDefective,
        String itemType

) {}