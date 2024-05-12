package com.example.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User serviceUser = User.builder()
//                .phoneNumber("service")
//                .password(passwordEncoder.encode("serv123"))
//                .authorities(UserConstants.SERVICE_AUTHORITY)
//                .email("no-reply@company.com")
//                .userIdentifier(UserIdentifier.SERVICE_ID)
//                .identifierValue("serv123")
//                .build();
//        userRepository.save(serviceUser);
    }
}
