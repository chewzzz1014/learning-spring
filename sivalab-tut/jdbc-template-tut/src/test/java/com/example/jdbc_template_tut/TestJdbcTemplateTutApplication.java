package com.example.jdbc_template_tut;

import org.springframework.boot.SpringApplication;

public class TestJdbcTemplateTutApplication {

	public static void main(String[] args) {
		SpringApplication.from(JdbcTemplateTutApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
