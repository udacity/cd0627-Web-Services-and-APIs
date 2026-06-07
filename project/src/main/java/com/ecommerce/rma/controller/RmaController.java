package com.ecommerce.rma.controller;

import com.ecommerce.rma.dto.ReturnRequest;
import com.ecommerce.rma.dto.ReturnResponse;
import com.ecommerce.rma.service.RmaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that exposes the Return Merchandise Authorization (RMA) endpoint.
 *
 * <p>The single endpoint ({@code POST /api/returns}) accepts a customer complaint,
 * runs it through the AI pipeline (Steps 3–4 in {@code RmaService}), and returns
 * a structured approval decision.
 *
 * <p>The endpoint is also protected by a <b>Resilience4j Circuit Breaker</b>
 * (Step 1) to handle OpenAI API outages gracefully.
 */
@Tag(name = "Returns", description = "AI-powered Return Merchandise Authorization API")
@RestController
@RequestMapping("/api/returns")
public class RmaController {

    private final RmaService rmaService;

    public RmaController(RmaService rmaService) {
        this.rmaService = rmaService;
    }

    // =========================================================================
    // TODO (Step 1 – REST Endpoint & Circuit Breaker):
    //
    // The method below is missing two annotations that are essential for it to
    // work correctly. Add them to the method signature:
    //
    // 1. @PostMapping
    //    Maps HTTP POST requests to /api/returns to this method.
    //    Without it, Spring does not register this as an endpoint and
    //    POST requests will return 404.
    //
    // 2. @CircuitBreaker(name = "rmaService", fallbackMethod = "submitReturnFallback")
    //    Wraps the method with a Resilience4j circuit breaker.
    //    - `name` must match a key in application.properties under
    //      resilience4j.circuitbreaker.instances.<name>
    //    - `fallbackMethod` must be the name of the fallback method below.
    //    If the OpenAI API throws exceptions repeatedly, the circuit opens and
    //    the fallback is called instead, preventing cascading failures.
    //
    // 3. Add Springdoc @Operation annotation for Swagger documentation:
    //    @Operation(
    //        summary = "Submit a return request",
    //        description = "Analyses the complaint with AI and returns an approval decision",
    //        responses = {
    //            @ApiResponse(responseCode = "200", description = "Decision returned",
    //                content = @Content(schema = @Schema(implementation = ReturnResponse.class))),
    //            @ApiResponse(responseCode = "503", description = "AI service unavailable (circuit open)")
    //        }
    //    )
    // =========================================================================

    /**
     * Submits a customer return request for AI-powered evaluation.
     *
     * @param request the return request body containing customerId and complaintText
     * @return a {@link ReturnResponse} containing the approval decision and reason
     */
    @PostMapping
    @Operation(
        summary = "Submit a return request",
        description = "Analyses the complaint with AI and returns an approval decision",
        responses = {
            @ApiResponse(responseCode = "200", description = "Decision returned",
                content = @Content(schema = @Schema(implementation = ReturnResponse.class))),
            @ApiResponse(responseCode = "503", description = "AI service unavailable (circuit open)")
        }
    )
    @CircuitBreaker(name = "rmaService", fallbackMethod = "submitReturnFallback")
    public ResponseEntity<ReturnResponse> submitReturn(@RequestBody ReturnRequest request) {
        return ResponseEntity.ok(rmaService.processReturn(request));
    }

    /**
     * Fallback method called when the circuit breaker is open or a call fails.
     *
     * @param request the original return request
     * @param ex      the exception that triggered the fallback
     * @return 503 Service Unavailable with a friendly error message
     */
    public ResponseEntity<ReturnResponse> submitReturnFallback(ReturnRequest request, Throwable ex) {
        var body = new ReturnResponse(false, "Service temporarily unavailable. Please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

}