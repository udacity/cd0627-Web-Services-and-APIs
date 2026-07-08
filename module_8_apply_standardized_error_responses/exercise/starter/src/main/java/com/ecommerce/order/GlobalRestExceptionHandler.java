package com.ecommerce.order;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    // TODO (Step 1) 1: Add Exception.class handler returning static support message (500)
    // TODO (Step 2) 2: Add OrderNotFoundException handler returning 404
    // TODO (Step 3) 3: Add InvalidOrderStateException handler returning 422 (UNPROCESSABLE_ENTITY)
    // TODO (Step 4) 4: Add MethodArgumentNotValidException handler returning 400. Iterate through BindingResult to format custom error string.
}
