package com.ecommerce.agent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    
    // TODO: Inject ChatClient and configure it with System message, functions and memory

    @GetMapping("/ask")
    public String ask(@RequestParam String message) {
        // TODO: Call ChatClient
        return "";
    }
}