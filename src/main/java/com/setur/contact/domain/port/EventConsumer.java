package com.setur.contact.domain.port;

@FunctionalInterface
public interface EventConsumer<T> {

    void consume(String topic, String partition, String offset, String groupId, T message);
}
