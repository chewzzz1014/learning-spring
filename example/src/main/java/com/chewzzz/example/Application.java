package com.chewzzz.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);

		MyFirstClass myFirstClass = context.getBean(MyFirstClass.class);
		System.out.println(myFirstClass.sayHello());
	}

}
