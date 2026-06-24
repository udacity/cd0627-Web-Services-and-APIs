package com.ecommerce.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final ChatClient chatClient;

    public ReviewController(ChatClient.Builder builder) {
        // TODO: Build the ChatClient
        this.chatClient = builder.build();
    }

    @PostMapping("/api/reviews/analyze")
    public ReviewSummary analyzeReview(@RequestBody String rawReview) {
        // TODO: Use the ChatClient fluent API to extract structured data into a ReviewSummary
        // TODO: Handle parsing exceptions by returning a fallback ReviewSummary

        return new ReviewSummary("Not Implemented", null, "UNKNOWN");
    }
}
