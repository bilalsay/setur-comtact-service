package com.setur.contact.infrastructure.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
public class EnvironmentConfig {

    @Value("${kafka.topics.producer.report.contact}")
    private String contactReportTopic;
}
