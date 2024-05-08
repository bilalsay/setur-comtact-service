package com.setur.contact.service;

import com.setur.contact.logger.TestLoggerExtension;
import com.setur.contact.mockito.MockitoExtension;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
class KafkaProducerServiceTest {

    @InjectMocks
    @Spy
    private KafkaProducerService<String, String> kafkaProducerService;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;


    @Test
    void shouldSendMessageSuccess_whenThereIsNoError() {
        var topicName = "test-topic";
        var key = "test-key";
        var message = "test-message";
        var metadata = new RecordMetadata(
                new TopicPartition("test-topic", 0),
                0L,
                0,
                0L,
                0,
                0
        );
        var producerRecord = new ProducerRecord<>(anyString(), anyString(), anyString());
        var sendResult = new SendResult<>(producerRecord, metadata);
        var future = new CompletableFuture<SendResult<String, String>>();
        future.complete(sendResult);
        when(kafkaTemplate.send(topicName, key, message)).thenReturn(future);
        kafkaProducerService.send(topicName, key, message);
        verify(kafkaTemplate).send(topicName, key, message);
    }

    @Test
    void shouldNotSendMessageSuccess_whenRuntimeException() {
        var topicName = "test-topic";
        var key = "test-topic";
        var message = "test-topic";
        var exceptionMessage = "Kafka send failed";
        var future = new CompletableFuture<SendResult<String, String>>();
        when(kafkaTemplate.send(anyString(), anyString(), anyString())).thenReturn(future);
        var exception = new RuntimeException(exceptionMessage);
        future.completeExceptionally(exception);
        kafkaProducerService.send(topicName, key, message);
        verify(kafkaTemplate).send(topicName, key, message);
    }

    @Test
    void shouldNotSendMessageSuccess_whenThrowKafkaException() {
        var topicName = "test-topic";
        var key = "test-topic";
        var message = "test-topic";
        var exceptionMessage = "Kafka error";
        doThrow(new KafkaException(exceptionMessage)).when(kafkaTemplate).send(topicName, key, message);
        kafkaProducerService.send(topicName, key, message);
        verify(kafkaTemplate).send(topicName, key, message);
    }

    @Test
    void shouldKafkaConnectionClose_whenKafkaTemplateIsNotNull() {
        kafkaProducerService.close();
        verify(kafkaProducerService).close();
    }

}
