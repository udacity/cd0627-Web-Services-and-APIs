package com.ecommerce.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

// TODO: Configure this interface as a LangChain4j AI Service.
public interface TravelAssistant {

    // TODO: Add a system message explaining the assistant's role (e.g., travel assistant).
    String chat(@UserMessage String message);
}
