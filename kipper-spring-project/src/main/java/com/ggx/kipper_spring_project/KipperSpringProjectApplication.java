package com.ggx.kipper_spring_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class KipperSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KipperSpringProjectApplication.class, args);
	}

}
