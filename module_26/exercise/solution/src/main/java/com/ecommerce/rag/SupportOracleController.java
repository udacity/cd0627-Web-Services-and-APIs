package com.ecommerce.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SupportOracleController {

    private final ChatClient chatClient;

    public SupportOracleController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
            .defaultSystem("If the provided context does not contain the answer, reply EXACTLY with 'I do not have enough information'.")
            .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore)
                .searchRequest(SearchRequest.builder().topK(2).similarityThreshold(0.80).build())
                .build())
            .build();
    }

    @GetMapping("/ask")
    public RagResponse ask(@RequestParam String question) {
        ChatResponse response = chatClient.prompt()
            .user(question)
            .call()
            .chatResponse();

        String answer = response.getResult().getOutput().getText();

        @SuppressWarnings("unchecked")
        List<Document> documents = (List<Document>) response.getMetadata().get(QuestionAnswerAdvisor.RETRIEVED_DOCUMENTS);

        List<String> sources = documents != null ? documents.stream()
            .map(doc -> (String) doc.getMetadata().getOrDefault("source", "unknown"))
            .distinct()
            .collect(Collectors.toList()) : List.of();

        return new RagResponse(answer, sources);
    }
}
