package com.ggx.sixtydays.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String userEmail, String subject, String body){
        System.out.printf("""
                From: %s
                Subject: %s
                
                %s
                """, userEmail,subject,body);
    }
}
