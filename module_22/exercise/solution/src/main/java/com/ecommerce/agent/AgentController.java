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

    @GetMapping("/api/agent/chat")
    public String chat(@RequestParam String message) {
        return travelAssistant.chat(message);
    }
}
