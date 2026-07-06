package com.ecommerce.search;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FaqController {

    private final VectorStore vectorStore;

    public FaqController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping("/search")
    public List<String> searchFaq(
            @RequestParam String query,
            @RequestParam(required = false) String category) {
        
        SearchRequest.Builder request = SearchRequest.builder().query(query).topK(3);
        
        if (category != null && !category.isBlank()) {
            request = request.filterExpression("category == '" + category + "'");
        }
        
        return vectorStore.similaritySearch(request.build()).stream()
                .map(Document::getText)
                .collect(Collectors.toList());
    }
}
