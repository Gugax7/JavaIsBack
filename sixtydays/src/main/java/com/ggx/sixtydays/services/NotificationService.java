package com.ggx.sixtydays.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    public void notifyUser(String userEmail){
        String subject = "Welcome";
        String body = "Thank you for registering on our service!";
        emailService.sendEmail(userEmail,subject,body);
    }
}
