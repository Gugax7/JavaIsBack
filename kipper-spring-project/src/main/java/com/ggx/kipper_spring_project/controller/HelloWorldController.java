package com.ggx.kipper_spring_project.controller;

import com.ggx.kipper_spring_project.domains.Order;
import com.ggx.kipper_spring_project.exceptions.PathNotFoundException;
import com.ggx.kipper_spring_project.services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    public String helloWorld(){
        throw new PathNotFoundException();
        //return helloWorldService.helloWorld("Guga");
    }

    @PostMapping
    public String helloPost(
            @RequestBody Order order
    ){
        return "Body posted: " + order.toString();
    }

    @GetMapping("/error")
    public String handleError(){
        return "error";
    }

}
