package com.ggx.sixtydays.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeBasedGreetingService implements GreetingService{

    @Autowired
    private MessageService messageService;


    @Override
    public String getGreeting() {
        LocalTime date = LocalTime.now();
        StringBuilder greeting = new StringBuilder();

        if (date.isBefore(LocalTime.NOON)){
            greeting.append("Good morning, ");
        }
        else if (date.isBefore(LocalTime.of(18,0))){
            greeting.append("Good afternoon, ");
        }
        else{
            greeting.append("Good evening, ");
        }

        greeting.append(messageService.getUserName());
        return greeting.toString();
    }
}
