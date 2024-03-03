package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TransactionInterf;

@RestController
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
//    @Bean
//    public static void calculate(){
//
//    }

    public DemoController() {
        logger.info("Creating Object: .."+this);
    }

    @Autowired
    TransactionInterf transactionInterf;


}
