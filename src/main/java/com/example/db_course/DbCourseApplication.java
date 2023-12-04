package com.example.db_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigurationProperties
@RestController
public class DbCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbCourseApplication.class, args);
		System.out.println("Hello world");
	}

	@GetMapping
	public String hello() {
		return "Hello World!";
	}
}
