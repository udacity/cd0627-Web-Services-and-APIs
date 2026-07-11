package com.ecommerce.agent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    private final TravelAssistant travelAssistant;

    public AgentController(TravelAssistant travelAssistant) {
        this.travelAssistant = travelAssistant;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String message) {
        return travelAssistant.chat("demo-user", message);
    }
}
