package com.lean.broker.reserva.infrastructure.email.impl;

import com.lean.broker.reserva.infrastructure.email.EmailService;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;
    private final Environment env;

    public EmailServiceImpl(JavaMailSender sender, Environment env) {
        this.sender = sender;
        this.env = env;
    }

    @Override
    public void enviarEmail(String asunto, String mensaje) throws MessagingException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        MimeMessage mnsg = sender.createMimeMessage();
        MimeMessageHelper msg = new MimeMessageHelper(mnsg, true);

        msg.setFrom(env.getProperty("brokerapp.mail.remitente"));
        msg.setTo(InternetAddress.parse(env.getProperty("brokerapp.mail.destinatario")));
        msg.setSubject(asunto);
        msg.setText(mensaje,true);
        sender.send(mnsg);
    }
}
