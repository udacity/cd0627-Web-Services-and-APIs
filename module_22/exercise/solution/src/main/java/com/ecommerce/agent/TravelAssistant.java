package com.ecommerce.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface TravelAssistant {

    @SystemMessage("You are a helpful travel assistant. You help users check weather and search for flights.")
    String chat(@UserMessage String message);
}
