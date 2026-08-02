package com.ecommerce.agent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    
    // TODO (Step 8): Inject TravelAssistant

    @GetMapping("/ask")
    public String ask(@RequestParam String message) {
        // TODO (Step 9): Call travelAssistant.chat("demo-user", message)
        return "";
    }
}
