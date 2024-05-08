package com.setur.contact.infrastructure.handler.event;

import com.setur.contact.domain.events.ReportGenerateEvent;
import com.setur.contact.domain.port.EventHandler;
import com.setur.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportGenerateEventHandler implements EventHandler<ReportGenerateEvent> {

    private final ContactService contactService;

    @Override
    @Retryable(
            retryFor = OptimisticLockingFailureException.class,
            backoff = @Backoff(random = true, delay = 1000, maxDelay = 5000, multiplier = 2),
            listeners = "defaultListenerSupport")
    public void handle(ReportGenerateEvent reportGenerateEvent) {

    }
}
