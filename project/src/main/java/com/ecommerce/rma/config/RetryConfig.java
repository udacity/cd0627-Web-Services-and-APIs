package com.ecommerce.rma.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.retry.RetryTemplate;

/**
 * Supplies a {@link RetryTemplate} when Spring AI retry auto-config is excluded
 * (required by OpenAI embedding/chat model beans).
 */
@Configuration
public class RetryConfig {

    @Bean
    @ConditionalOnMissingBean
    RetryTemplate retryTemplate() {
        return new RetryTemplate();
    }
}
