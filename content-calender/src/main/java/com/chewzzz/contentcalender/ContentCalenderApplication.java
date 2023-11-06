package com.chewzzz.contentcalender;

import com.chewzzz.contentcalender.config.ContentCalenderProperties;
import com.chewzzz.contentcalender.model.Content;
import com.chewzzz.contentcalender.model.Status;
import com.chewzzz.contentcalender.model.Type;
import com.chewzzz.contentcalender.respository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@EnableConfigurationProperties(ContentCalenderProperties.class)
@SpringBootApplication
public class ContentCalenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentCalenderApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ContentRepository contentRepository) {
		return args -> {
			// insert data into db
			Content content = new Content(
					null,
					"Saying Hello from SpringBoot",
					"Happy learning SpringBoot!",
					Status.IDEA,
					Type.VIDEO,
					LocalDateTime.now(),
					null,
					""
			);
			contentRepository.save(content);
		};
	}

}
