package com.setur.contact.converter;

import com.setur.contact.util.ObjectMapperUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.converter.MessagingMessageConverter;

import java.lang.reflect.Type;


public class KafkaMessageConverter extends MessagingMessageConverter {

    @Override
    protected Object extractAndConvertValue(ConsumerRecord<?, ?> consumerRecord, Type type) {
        Object value = consumerRecord.value();
        return ObjectMapperUtil.extractTreeValue(value, type);
    }
}