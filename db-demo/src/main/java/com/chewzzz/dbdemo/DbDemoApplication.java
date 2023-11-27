package com.chewzzz.dbdemo;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Log
@SpringBootApplication
public class DbDemoApplication implements CommandLineRunner {

	private final DataSource dataSource;

	public DbDemoApplication (DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public static void main(String[] args) {
		SpringApplication.run(DbDemoApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		log.info("Datasource: " + dataSource.toString());
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("select 1");
	}
}
