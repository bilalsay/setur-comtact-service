package com.setur.contact.infrastructure.configuration.kafka.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class KafkaProducerConfigData {

    @Value("${kafka-producer-config.acks}")
    private String acks;

    @Value("${kafka-producer-config.batch-size}")
    private Integer batchSize;

    @Value("${kafka-producer-config.batch-size-boost-factor}")
    private Integer batchSizeBoostFactor;

    @Value("${kafka-producer-config.linger-ms}")
    private Integer lingerMs;

    @Value("${kafka-producer-config.request-timeout-ms}")
    private Integer requestTimeoutMs;

    @Value("${kafka-producer-config.retry-count}")
    private Integer retryCount;
}