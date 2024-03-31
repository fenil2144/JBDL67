package com.example.minorproject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.minorproject.models.Author;
import com.example.minorproject.repository.AuthorRepositoryInterf;

@SpringBootApplication
public class MinorprojectApplication implements CommandLineRunner {

	@Autowired
	AuthorRepositoryInterf authorRepositoryInterf;

	private static Logger logger = LoggerFactory.getLogger(MinorprojectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MinorprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("In Run function of main class");

//		List<Author> authorList =  authorRepositoryInterf
//				.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(30, "India", "p");
//
//		authorList.forEach(System.out::println);
	}
}
