package com.ecommerce.ai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

/**
 * Configures a buffered RestClient to ensure compatibility with OpenAI-compatible
 * proxies (like Vocareum) that don't support HTTP chunked transfer encoding.
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder()
                .requestFactory(new BufferingClientHttpRequestFactory(
                        new SimpleClientHttpRequestFactory()));
    }
}
