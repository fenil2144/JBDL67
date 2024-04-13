package com.example.springSecurityDemo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springSecurityDemo.model.MyUser;
import com.example.springSecurityDemo.repository.MyUserRepository;

@SpringBootApplication
public class SpringSecurityDemoApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		MyUser myUser1 = MyUser.builder()
//				.name("john").email("john@gmail.com")
//				.password(passwordEncoder.encode("john123"))
//				.authority("dev")
//				.build();
//
//		MyUser myUser2 = MyUser.builder()
//				.name("preet").email("preet@gmail.com")
//				.password(passwordEncoder.encode("preet123"))
//				.authority("qa,admin")
//				.build();
//
//		myUserRepository.saveAll(Arrays.asList(myUser1,myUser2));

	}
}
