package com.ecommerce.agent;

// TODO (Step 1): Import AiService and SystemMessage from langchain4j
// TODO (Step 2): Annotate this interface with @AiService
public interface TravelAssistant {
    // TODO (Step 3): Add @SystemMessage defining the persona
    String chat(String userMessage);
}
