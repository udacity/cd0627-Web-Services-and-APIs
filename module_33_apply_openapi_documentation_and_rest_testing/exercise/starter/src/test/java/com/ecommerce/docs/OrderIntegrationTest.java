package com.ecommerce.docs;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// TODO (Step 1): Annotate with @SpringBootTest and @AutoConfigureMockMvc
// TODO (Step 2): Write a test that creates an order, cancels it, then retrieves it
//                and asserts the status is "CANCELLED"
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCancelAndVerifyOrder() throws Exception {
        // TODO (Step 3): POST /orders to create an order and capture the returned id
        // TODO (Step 4): POST /orders/{id}/cancel and assert status is 204 No Content
        // TODO (Step 5): GET /orders/{id} and assert status is "CANCELLED"
    }
}
