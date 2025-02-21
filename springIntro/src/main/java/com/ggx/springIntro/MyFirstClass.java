package com.ggx.springIntro;

import org.springframework.stereotype.Component;

public class MyFirstClass {

    private String myVar;

    public MyFirstClass(String myVar) {
        this.myVar = myVar;
    }

    public String hello(){
        return "Hello from my first class! " + myVar;
    }
}
