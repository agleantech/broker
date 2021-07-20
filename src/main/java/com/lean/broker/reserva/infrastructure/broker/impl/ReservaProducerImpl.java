package com.lean.broker.reserva.infrastructure.broker.impl;

import com.lean.broker.reserva.infrastructure.broker.ReservaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservaProducerImpl implements ReservaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "kafka_test";

    public ReservaProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarMensaje(String mensaje) {
        kafkaTemplate.send(TOPIC, mensaje);
    }
}
