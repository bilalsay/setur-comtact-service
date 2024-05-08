package com.setur.contact.infrastructure.configuration.retryable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
public class EnableRetryConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        return RetryTemplate.builder()
        .withListener(new DefaultListenerSupport())

        .exponentialBackoff(1000, 3, 3000, true)

        .retryOn(Exception.class)

        .build();
    }
}
