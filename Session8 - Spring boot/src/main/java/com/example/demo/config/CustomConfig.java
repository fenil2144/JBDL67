package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.DemoController;

@Configuration
public class CustomConfig {

    private static Logger logger = LoggerFactory.getLogger(CustomConfig.class);

//    @Autowired
//    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        logger.info("In CustomConfig: {}",restTemplate);
        return restTemplate;
    }


}
