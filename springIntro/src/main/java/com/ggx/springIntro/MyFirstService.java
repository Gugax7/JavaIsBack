package com.ggx.springIntro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

    private final MyFirstClass myFirstClass;

    public MyFirstService(@Qualifier("secondBean") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellStory(){
        return "the dependency is saying: " + myFirstClass.hello();
    }
}
