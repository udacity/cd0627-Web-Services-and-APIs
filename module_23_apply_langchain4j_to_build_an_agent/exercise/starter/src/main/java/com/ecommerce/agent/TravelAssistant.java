package com.ecommerce.agent;

// TODO (Step 1): Import AiService and SystemMessage from langchain4j
// TODO (Step 2): Annotate this interface with @AiService
public interface TravelAssistant {
    // TODO (Step 3): Add @SystemMessage defining the persona
    // TODO (Step 5): Add @MemoryId String chatId, and @UserMessage before the parameters
    // You will need to import dev.langchain4j.service.MemoryId and dev.langchain4j.service.UserMessage
    String chat(String chatId, String userMessage);
}
