package com.ggx.kipper_spring_project.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class HelloWorldService {


    @GetMapping
    public String helloWorld(String name){
        return "Hello, " + name + "!";
    }
}
