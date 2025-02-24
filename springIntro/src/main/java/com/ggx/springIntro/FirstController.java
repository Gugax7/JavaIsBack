package com.ggx.springIntro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {

    @GetMapping("hello/{user-name}")
    public String sayHello(
            @PathVariable("user-name") String userName
    ){
        return "Hello, " + userName;
    }
    //http://localhost:8080/hello?param_one=value_one&param_two=value_two
    @GetMapping("hello")
    public String paramVar(
            @RequestParam("user-name") String userName,
            @RequestParam("user-age") int userAge
    ){
        return "Hello, " + userName + " oh, you are " + userAge + " years old";
    }

    @PostMapping("post")
    public String post(@RequestBody String message){
        return "Request completed! and the message is: " + message;
    }

    @PostMapping("post-order")
    public String post(@RequestBody Order order){
        return "Request completed! and the order is: " + order.toString();
    }

    @PostMapping("post-order-record")
    public String post(@RequestBody OrderRecord orderRecord){
        return "Request completed! and the order is: " + orderRecord.toString();
    }
}
