package com.ggx.sixtydays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SixtydaysApplication {

	public static void main(String[] args) {
		var myApp = SpringApplication.run(SixtydaysApplication.class, args);
		for(String beanName : myApp.getBeanDefinitionNames()){
			System.out.println(beanName);
		}
	}

}
