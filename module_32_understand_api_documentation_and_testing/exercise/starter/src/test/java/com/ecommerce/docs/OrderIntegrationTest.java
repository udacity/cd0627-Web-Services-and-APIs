package com.ecommerce.docs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// TODO (Step 1): Implement the integration test
// TODO (Step 2): POST /orders to create, then GET /orders/{id} to retrieve and assert persistence
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAndRetrieveOrder() throws Exception {
        // TODO (Step 3): POST an order and capture the returned id
        // TODO (Step 4): GET /orders/{id} and assert status 200 and body fields
    }
}
