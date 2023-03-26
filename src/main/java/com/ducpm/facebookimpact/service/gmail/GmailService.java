package com.ducpm.facebookimpact.service.gmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("minhduc180699@gmail.com");
        message.setSubject("Like Service");
        message.setText("This is a test email");
        javaMailSender.send(message);
    }
}
