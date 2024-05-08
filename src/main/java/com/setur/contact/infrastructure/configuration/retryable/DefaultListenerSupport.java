package com.setur.contact.infrastructure.configuration.retryable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultListenerSupport implements RetryListener {

    @Override
    public <T, E extends Throwable> void close(RetryContext context,
                                               RetryCallback<T, E> callback, Throwable throwable) {
        log.trace("Finished retryable method {}", context.getAttribute("context.name"));
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
                                                 Throwable throwable) {
        log.warn("Retryable method {} threw {} th exception {}",
                context.getAttribute("context.name"), context.getRetryCount(), throwable.toString());
    }

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context,
                                                 RetryCallback<T, E> callback) {
        log.trace("Starting retryable method {}", context.getAttribute("context.name"));
        return true;
    }
}
