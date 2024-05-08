package com.setur.contact.domain.port;

import org.springframework.context.event.EventListener;

public interface EventHandler<T> {

    @EventListener
    void handle(T event);
}
