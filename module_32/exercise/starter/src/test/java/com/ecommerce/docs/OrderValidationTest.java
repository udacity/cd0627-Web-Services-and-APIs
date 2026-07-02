package com.ecommerce.docs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// TODO: Write a WebMvcTest for invalid payload
// TODO: Assert HTTP 400 and RFC 7807 problem detail fields
@WebMvcTest(OrderController.class)
class OrderValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void emptyItemIds_returnsBadRequest() throws Exception {
        // TODO: Submit POST /orders with {"itemIds":[]}
        // TODO: Assert status is 400 Bad Request
        // TODO: Assert jsonPath("$.type") exists
        // TODO: Assert jsonPath("$.title").value("Bad Request")
        // TODO: Assert jsonPath("$.detail") contains "empty"
    }
}
