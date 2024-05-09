package com.setur.contact.infrastructure.configuration.event;

import com.setur.contact.domain.events.ReportGenerateEvent;
import com.setur.contact.infrastructure.configuration.EnvironmentConfig;
import com.setur.contact.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportGenerateEventHandler {

    private final KafkaProducerService<String, ReportGenerateEvent> kafkaProducerService;

    private final EnvironmentConfig environmentConfig;

    @Retryable(
            retryFor = OptimisticLockingFailureException.class,
            backoff = @Backoff(random = true, delay = 1000, maxDelay = 5000, multiplier = 2),
            listeners = "defaultListenerSupport")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ReportGenerateEvent event) {
        kafkaProducerService.send(environmentConfig.getContactReportTopic(), UUID.randomUUID().toString(), event);
    }
}
