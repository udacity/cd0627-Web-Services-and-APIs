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
        
        SearchRequest request = SearchRequest.query(query).withTopK(3);
        
        if (category != null && !category.isBlank()) {
            request = request.withFilterExpression("category == '" + category + "'");
        }
        
        return vectorStore.similaritySearch(request).stream()
                .map(Document::getContent)
                .collect(Collectors.toList());
    }
}
