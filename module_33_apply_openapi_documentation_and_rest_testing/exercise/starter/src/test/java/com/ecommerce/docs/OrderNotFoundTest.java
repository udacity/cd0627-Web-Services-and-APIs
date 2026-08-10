package com.ecommerce.docs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// TODO (Step 6): Annotate with @WebMvcTest(OrderController.class)
// TODO (Step 7): Use Mockito.when() to make orderService.getOrder("FAKE-999")
//                throw an OrderNotFoundException
@WebMvcTest(OrderController.class)
class OrderNotFoundTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void getNonExistentOrder_returns404() throws Exception {
        // TODO (Step 7): Mock orderService.getOrder("FAKE-999") to throw OrderNotFoundException
        // TODO (Step 8): Perform GET /orders/FAKE-999 and assert:
        //   - status is 404 Not Found
        //   - jsonPath("$.type") exists
        //   - jsonPath("$.title").value("Not Found")
        //   - jsonPath("$.detail") exists
    }
}
