package com.ecommerce.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
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
        // TODO (Step 5): Configure QuestionAnswerAdvisor with a custom SearchRequest (.topK(2).similarityThreshold(0.50))
        this.chatClient = builder
            // .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).searchRequest(SearchRequest.builder().topK(2).similarityThreshold(0.80).build()).build())
            // TODO (Step 6): Inject a strict system prompt regarding out-of-scope questions
            // .defaultSystem("If the provided context does not contain the answer, reply EXACTLY with 'I do not have enough information'.")
            .build();
    }

    @GetMapping("/ask")
    public RagResponse ask(@RequestParam String question) {
        // TODO (Step 7): Execute prompt, return answer and extract sources from context metadata
        return new RagResponse("Dummy response", List.of("dummy-source"));
    }
}
