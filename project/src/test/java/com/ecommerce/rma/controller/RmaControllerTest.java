package com.ecommerce.rma.controller;

import com.ecommerce.rma.dto.ReturnRequest;
import com.ecommerce.rma.dto.ReturnResponse;
import com.ecommerce.rma.service.RmaService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Slice test for {@link RmaController}.
 *
 * <p>{@code @WebMvcTest} bootstraps only the Web MVC layer (controllers, filters,
 * converters) without starting the full Spring context. This makes the test fast
 * and isolated — no Kafka, no OpenAI, no VectorStore required.
 *
 * <p>{@code @ExtendWith(MockitoExtension.class)} enables Mockito to mock the
 * {@code RmaService} and inject it into the {@code RmaController} without
 * starting a full Spring ApplicationContext.
 */
@ExtendWith(MockitoExtension.class)
class RmaControllerTest {

    private MockMvc mockMvc;

    // TODO (Step 2 – Unit Test Setup):
    // @Mock creates a Mockito mock of the RmaService.
    // @InjectMocks creates an instance of RmaController and injects the mock into it.
    @Mock
    private RmaService rmaService;

    @InjectMocks
    private RmaController rmaController;

    @BeforeEach
    void setUp() {
        // Initialize MockMvc in standalone mode for the controller
        this.mockMvc = MockMvcBuilders.standaloneSetup(rmaController).build();
    }

    /**
     * Happy-path test: when {@code RmaService.processReturn()} returns an approved
     * response, the controller should return HTTP 200 with the correct JSON body.
     *
     * <p>After completing Step 1 (adding @PostMapping to the controller), this test
     * should pass. Run it with:
     * <pre>./mvnw test -Dtest=RmaControllerTest</pre>
     */
    @Test
    void testSubmitReturn_approved_returns200() throws Exception {
        // Arrange: mock the service to return an approved response
        when(rmaService.processReturn(any(ReturnRequest.class)))
                .thenReturn(new ReturnResponse(true, "Approved: defective electronics within 30-day window."));

        String jsonPayload = """
                {
                    "customerId": "CUST-123",
                    "complaintText": "My laptop screen cracked after 2 weeks of normal use."
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/api/returns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.approved").value(true))
                .andExpect(jsonPath("$.reason").isNotEmpty());
    }

    // =========================================================================
    // TODO (Step 2 – Additional Test Cases):
    //
    // Add a test for the DENIED case: the service returns approved=false, and
    // the controller should still return HTTP 200 with approved=false in the body.
    //
    // Template:
    //
    //   @Test
    //   void testSubmitReturn_denied_returns200WithApprovedFalse() throws Exception {
    //       when(rmaService.processReturn(any(ReturnRequest.class)))
    //           .thenReturn(new ReturnResponse(false, "Change of mind returns are not accepted."));
    //
    //       String jsonPayload = """
    //               {
    //                   "customerId": "CUST-456",
    //                   "complaintText": "I changed my mind about this jacket."
    //               }
    //               """;
    //
    //       mockMvc.perform(post("/api/returns")
    //                       .contentType(MediaType.APPLICATION_JSON)
    //                       .content(jsonPayload))
    //               .andExpect(status().isOk())
    //               .andExpect(jsonPath("$.approved").value(false));
    //   }
    //
    // Bonus: After completing Step 1 (circuit breaker fallback), add a test that
    // makes the service throw a RuntimeException and verifies the fallback returns 503.
    // =========================================================================

    @Test
    void testSubmitReturn_denied_returns200WithApprovedFalse() throws Exception {
        when(rmaService.processReturn(any(ReturnRequest.class)))
                .thenReturn(new ReturnResponse(false, "Change of mind returns are not accepted."));

        String jsonPayload = """
                {
                    "customerId": "CUST-456",
                    "complaintText": "I changed my mind about this jacket."
                }
                """;

        mockMvc.perform(post("/api/returns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.approved").value(false));
    }
}