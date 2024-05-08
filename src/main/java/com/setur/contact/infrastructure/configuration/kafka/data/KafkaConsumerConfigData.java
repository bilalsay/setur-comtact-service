package com.setur.contact.infrastructure.configuration.kafka.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class KafkaConsumerConfigData {

    @Value("${kafka-consumer-config.key-deserializer}")
    private String keyDeserializer;

    @Value("${kafka-consumer-config.value-deserializer}")
    private String valueDeserializer;

    @Value("${kafka-consumer-config.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${kafka-consumer-config.batch-listener}")
    private Boolean batchListener;

    @Value("${kafka-consumer-config.auto-startup}")
    private Boolean autoStartup;

    @Value("${kafka-consumer-config.concurrency-level}")
    private Integer concurrencyLevel;

    @Value("${kafka-consumer-config.session-timeout-ms}")
    private Integer sessionTimeoutMs;

    @Value("${kafka-consumer-config.heartbeat-interval-ms}")
    private Integer heartbeatIntervalMs;

    @Value("${kafka-consumer-config.max-poll-interval-ms}")
    private Integer maxPollIntervalMs;

    @Value("${kafka-consumer-config.poll-timeout-ms}")
    private Long pollTimeoutMs;

    @Value("${kafka-consumer-config.max-partition-fetch-bytes-default}")
    private Integer maxPartitionFetchBytesDefault;

    @Value("${kafka-consumer-config.max-partition-fetch-bytes-boost-factor}")
    private Integer maxPartitionFetchBytesBoostFactor;
}
