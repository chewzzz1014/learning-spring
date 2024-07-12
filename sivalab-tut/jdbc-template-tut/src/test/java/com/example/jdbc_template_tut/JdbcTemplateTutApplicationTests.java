package com.example.jdbc_template_tut;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class JdbcTemplateTutApplicationTests {

	@Test
	void contextLoads() {
	}

}
