package com.ecommerce.agent;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.SystemMessage;

@AiService
public interface TravelAssistant {
    @SystemMessage("You are an expert travel assistant. You have access to tools to check flights and weather.")
    String chat(String userMessage);
}
