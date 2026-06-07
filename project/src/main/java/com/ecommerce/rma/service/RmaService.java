package com.ecommerce.rma.service;

import com.ecommerce.rma.dto.ReturnAnalysis;
import com.ecommerce.rma.dto.ReturnRequest;
import com.ecommerce.rma.dto.ReturnResponse;
import com.ecommerce.rma.event.ReturnApprovedEvent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.ecommerce.rma.config.KafkaLocalConfig.RETURNS_TOPIC;

/**
 * Core business logic for the AI-powered Return Merchandise Authorization engine.
 *
 * <p>This service orchestrates a two-step AI pipeline:
 * <ol>
 *   <li><b>Step 3 – Complaint Analysis:</b> Uses a plain {@code ChatClient} call with a
 *       {@link BeanOutputConverter} to parse the customer's free-text complaint into a
 *       structured {@link ReturnAnalysis} record.</li>
 *   <li><b>Step 4 – Policy Check (RAG):</b> Uses a second {@code ChatClient} call with a
 *       {@link QuestionAnswerAdvisor} to ask the AI whether the analysed return warrants a
 *       refund, grounding its answer in the return-policy documents stored in the
 *       {@link VectorStore}.</li>
 * </ol>
 * If the policy check approves the return, the service publishes a
 * {@link ReturnApprovedEvent} to Kafka (Step 5).
 */
@Service
public class RmaService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Constructor injection. Spring Boot auto-configures:
     * <ul>
     *   <li>{@code ChatClient.Builder} – wired to OpenAI via the API key in
     *       {@code application.properties}</li>
     *   <li>{@code VectorStore} – the in-memory store seeded with policy text in
     *       {@link com.ecommerce.rma.config.AiConfig}</li>
     *   <li>{@code KafkaTemplate} – wired to the embedded broker started in
     *       {@link com.ecommerce.rma.config.KafkaLocalConfig}</li>
     * </ul>
     */
    public RmaService(ChatClient.Builder chatClientBuilder,
                      VectorStore vectorStore,
                      KafkaTemplate<String, Object> kafkaTemplate) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Processes a customer return request end-to-end.
     *
     * @param request the incoming request containing customerId and complaintText
     * @return a {@link ReturnResponse} with an approval decision and reason
     */
    public ReturnResponse processReturn(ReturnRequest request) {

        // =======================================================================
        // Step 3 – Structured AI Output: Complaint Analysis
        // =======================================================================
        BeanOutputConverter<ReturnAnalysis> converter = new BeanOutputConverter<>(ReturnAnalysis.class);

        String prompt = """
            Analyse the following customer complaint and classify it.
            %s
            Complaint: %s
            """.formatted(converter.getFormat(), request.complaintText());

        String rawJson = chatClient.prompt()
            .user(prompt)
            .call()
            .content();

        ReturnAnalysis analysis = converter.convert(rawJson);

        // =======================================================================
        // Step 4 – Retrieval-Augmented Generation: Policy Check
        // =======================================================================
        QuestionAnswerAdvisor policyAdvisor = new QuestionAnswerAdvisor(vectorStore);

        String policyQuestion = """
            Based on the company return policy, should this return be approved?
            Item type  : %s
            Is defective: %s
            Customer sentiment: %s
            Answer with 'APPROVED' or 'DENIED' followed by a brief reason.
            """.formatted(analysis.itemType(), analysis.isDefective(), analysis.sentiment());

        String policyDecision = chatClient.prompt()
            .user(policyQuestion)
            .advisors(policyAdvisor)
            .call()
            .content();

        boolean approved = policyDecision != null && policyDecision.toUpperCase().startsWith("APPROVED");

        // =======================================================================
        // Step 5 – Kafka Event Publishing
        // =======================================================================
        if (approved) {
            var event = new ReturnApprovedEvent(
                request.customerId(),
                analysis.itemType(),
                policyDecision
            );
            kafkaTemplate.send(RETURNS_TOPIC, request.customerId(), event);
        }

        return new ReturnResponse(approved, policyDecision);
    }
}