package com.example.minorproject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.models.Admin;
import com.example.minorproject.models.Author;
import com.example.minorproject.models.MyUser;
import com.example.minorproject.models.Student;
import com.example.minorproject.repository.AdminRepositoryInterf;
import com.example.minorproject.repository.AuthorRepositoryInterf;
import com.example.minorproject.repository.MyUserRepositoryInterf;
import com.example.minorproject.repository.StudentRespositoryInterf;

@SpringBootApplication
public class MinorprojectApplication implements CommandLineRunner {

	@Autowired
	StudentRespositoryInterf studentRespositoryInterf;

	@Autowired
	MyUserRepositoryInterf myUserRepositoryInterf;

	@Autowired
	AdminRepositoryInterf adminRepositoryInterf;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${users.authority.student}")
	String studentAuthority;

	@Value("${users.authority.admin}")
	String adminAuthority;

	private static Logger logger = LoggerFactory.getLogger(MinorprojectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MinorprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("In Run function of main class");
//
//		MyUser myUser = MyUser.builder()
//				.username("raj").password(passwordEncoder.encode("raj123"))
//				.authority(adminAuthority).build();
//
//		myUser = myUserRepositoryInterf.save(myUser);
//
//		Admin admin = Admin.builder()
//				.age(45).name("Raj Shah").email("raj@gmail.com").myUser(myUser)
//				.build();
//
//		adminRepositoryInterf.save(admin);
//
//		MyUser studentUser = MyUser.builder()
//				.username("john").password(passwordEncoder.encode("john123"))
//				.authority(studentAuthority).build();
//
//		studentUser = myUserRepositoryInterf.save(studentUser);
//
//		Student student = Student.builder()
//				.name("John Cena")
//				.email("john@gmail.com")
//				.address("India")
//				.accountStatus(AccountStatus.ACTIVE)
//				.contact("9876543212")
//				.myUser(studentUser)
//				.build();
//
//		studentRespositoryInterf.save(student);
	}
}
