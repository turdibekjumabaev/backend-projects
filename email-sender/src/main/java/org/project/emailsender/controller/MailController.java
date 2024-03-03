package org.project.emailsender.controller;

import org.project.emailsender.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/send")
    public ResponseEntity<Void> sendMail(String email, String subject, String body){
        mailService.sendSimpleEmail(email, subject, body);

        return ResponseEntity.ok().build();
    }
}
