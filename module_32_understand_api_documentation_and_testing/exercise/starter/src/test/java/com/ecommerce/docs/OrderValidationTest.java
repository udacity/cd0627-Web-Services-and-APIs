package com.ecommerce.docs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// TODO (Step 5): Write a WebMvcTest for invalid payload
// TODO (Step 6): Assert HTTP 400 and RFC 7807 problem detail fields
@WebMvcTest(OrderController.class)
class OrderValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void emptyItemIds_returnsBadRequest() throws Exception {
        // TODO (Step 7): Submit POST /orders with {"itemIds":[]}
        // TODO (Step 8): Assert status is 400 Bad Request
        // TODO (Step 9): Assert jsonPath("$.type") exists
        // TODO (Step 10): Assert jsonPath("$.title").value("Bad Request")
        // TODO (Step 11): Assert jsonPath("$.detail") contains "empty"
    }
}
