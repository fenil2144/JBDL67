package com.example.proxyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProxyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyAppApplication.class, args);
	}

}
