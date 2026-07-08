package com.ecommerce.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
    private final ChatClient chatClient;

    public ReviewController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/api/reviews/analyze")
    public ReviewSummary analyzeReview(@RequestBody String rawReview) {
        try {
            return chatClient.prompt()
                    .system("Analyze the following product review and extract the key points.")
                    .user(rawReview)
                    .call()
                    .entity(ReviewSummary.class);
        } catch (Exception e) {
            log.error("Failed to parse LLM output into ReviewSummary", e);
            return new ReviewSummary("Analysis Failed", List.of("Could not parse review"), "UNKNOWN");
        }
    }
}
