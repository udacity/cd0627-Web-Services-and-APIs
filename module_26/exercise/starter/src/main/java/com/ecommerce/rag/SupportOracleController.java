package com.ecommerce.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupportOracleController {

    private final ChatClient chatClient;

    public SupportOracleController(ChatClient.Builder builder, VectorStore vectorStore) {
        // TODO: Configure QuestionAnswerAdvisor with a custom SearchRequest (.withTopK(2).withSimilarityThreshold(0.80))
        this.chatClient = builder
            // .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.query("").withTopK(2).withSimilarityThreshold(0.80)))
            // TODO: Inject a strict system prompt regarding out-of-scope questions
            // .defaultSystem("If the provided context does not contain the answer, reply EXACTLY with 'I do not have enough information'.")
            .build();
    }

    @GetMapping("/ask")
    public RagResponse ask(@RequestParam String question) {
        // TODO: Execute prompt, return answer and extract sources from context metadata
        return new RagResponse("Dummy response", List.of("dummy-source"));
    }
}
