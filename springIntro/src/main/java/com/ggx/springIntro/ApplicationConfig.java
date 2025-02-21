package com.ggx.springIntro;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    @Qualifier("firstBean")
    public MyFirstClass myFirstBean(){
        return new MyFirstClass("from first bean");
    }

    @Bean
    @Qualifier("secondBean")
    public MyFirstClass mySecondBean(){
        return new MyFirstClass("from second bean");
    }
}
