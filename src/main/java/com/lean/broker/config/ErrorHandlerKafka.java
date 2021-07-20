package com.lean.broker.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.TopicPartition;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerAwareErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ErrorHandlerKafka implements ContainerAwareErrorHandler {
    private final KafkaTemplate kafkaTemplate;
    private final Environment environment;

    public ErrorHandlerKafka(KafkaTemplate kafkaTemplate, Environment environment) {
        this.kafkaTemplate = kafkaTemplate;
        this.environment = environment;
    }

    @Override
    public void handle(Exception e, List<ConsumerRecord<?, ?>> list, Consumer<?, ?> consumer, MessageListenerContainer messageListenerContainer) {
        ConsumerRecord<?,?> record = list.get(0);
        try {
            kafkaTemplate.send(environment.getProperty("brokerapp.kafka.topic"), record.key(), record.value());
            consumer.seek(new TopicPartition(record.topic(), record.partition()), record.offset() + 1);
        } catch (Exception ex) {
            consumer.seek(new TopicPartition(record.topic(), record.partition()), record.offset());
            throw new KafkaException("Falla broker ", ex);
        }
    }
}