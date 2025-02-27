package com.ggx.sixtydays;

import com.ggx.sixtydays.config.AppConfig;
import com.ggx.sixtydays.services.GreetingService;
import com.ggx.sixtydays.services.MessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class SixtydaysApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);

		MessageService messageService = context.getBean(MessageService.class);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		messageService.setUserName(name);

		GreetingService greetingService = context.getBean(GreetingService.class);
		String personalizedGreeting = greetingService.getGreeting();
		System.out.println(personalizedGreeting);

	}

}
