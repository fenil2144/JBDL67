package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Main Class");

		logger.trace("I am trace log");
		logger.debug("I am debug log");
		logger.info("I am info log");
		logger.warn("I am warn log");
		logger.error("I am error log");

	}

}
