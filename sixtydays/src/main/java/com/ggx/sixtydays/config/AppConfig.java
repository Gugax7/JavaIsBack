package com.ggx.sixtydays.config;


import com.ggx.sixtydays.services.EmailService;
import com.ggx.sixtydays.services.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com/ggx/sixtydays/services")
public class AppConfig {

//    @Bean
//    public EmailService emailService(){
//        return new EmailService();
//    }
//
//    @Bean
//    public NotificationService notificationService(){
//        return new NotificationService();
//    }

}
