package org.project.emailsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("<your email addres>");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }
}