package com.ggx.springIntro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("hello")
    public String sayHello(){
        return "<h1>Hello World!</h1>";
    }

    @PostMapping("post")
    public String post(@RequestBody String message){
        return "Request completed! and the message is: " + message;
    }
}
