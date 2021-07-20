package com.lean.broker.reserva.infrastructure.broker;

import com.lean.broker.reserva.infrastructure.email.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReservaConsumer {

    private static final String TOPIC = "kafka_test";
    private final EmailService emailService;

    public ReservaConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = TOPIC, groupId = "default")
    public void consume(String message) {
        try {
            emailService.enviarEmail("Información reserva", message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
