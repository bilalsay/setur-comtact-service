package com.setur.contact.service;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerService<K extends Serializable, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    @Retryable(retryFor = KafkaException.class, backoff = @Backoff(delay = 200L))
    public void send(String topicName, K key, V message) {
        try {
            var kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.whenComplete((kvSendResult, throwable) -> {
                if (throwable == null) {
                    log.info("Received successful response from Kafka for key: {}"
                                    + " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                            key,
                            kvSendResult.getRecordMetadata().topic(),
                            kvSendResult.getRecordMetadata().partition(),
                            kvSendResult.getRecordMetadata().offset(),
                            kvSendResult.getRecordMetadata().timestamp());
                } else {
                    log.warn("Unable to send message {} due to {}", message, throwable.getMessage());
                }
            });
        } catch (KafkaException e) {
            log.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
        }
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}
