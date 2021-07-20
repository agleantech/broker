package com.lean.broker.reserva.infrastructure.email;

import javax.mail.MessagingException;

public interface EmailService {

    void enviarEmail(String asunto, String mensaje) throws MessagingException;
}
