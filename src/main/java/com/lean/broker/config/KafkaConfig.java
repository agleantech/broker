package com.lean.broker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class KafkaConfig {

    private final ErrorHandlerKafka errorHandler;

    public KafkaConfig(ErrorHandlerKafka errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory containerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setErrorHandler(errorHandler);
        return factory;
    }

}
