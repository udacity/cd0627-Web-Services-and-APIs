package com.ecommerce.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface TranslatorAgent {

    @SystemMessage("You translate English to French.")
    String translate(String text);
}
