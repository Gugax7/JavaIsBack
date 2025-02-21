package com.ggx.springIntro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringIntroApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(SpringIntroApplication.class, args);
		MyFirstService firstService = ctx.getBean(MyFirstService.class);
		System.out.println(firstService.tellStory());
	}





}
