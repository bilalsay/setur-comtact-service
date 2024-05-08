package com.setur.contact.infrastructure.consumer;

import com.setur.contact.domain.events.ReportGenerateEvent;
import com.setur.contact.domain.port.EventConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReportGenerateEventConsumer implements EventConsumer<ReportGenerateEvent> {

    private final ApplicationEventPublisher publisher;

    @Override
    @KafkaListener(topics = "${kafka.topics.consumer.report.contact}",
            groupId = "${kafka.topics.consumer.group-id}",
            autoStartup = "${kafka.consume.enable:true}")
    public void consume(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                        @Header(KafkaHeaders.OFFSET) String offset,
                        @Header(KafkaHeaders.GROUP_ID) String groupId,
                        @Payload ReportGenerateEvent reportGenerateEvent) {

    }
}
