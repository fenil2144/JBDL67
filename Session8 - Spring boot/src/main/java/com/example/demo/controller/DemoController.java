package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Demo;
import com.example.demo.service.PaymentInterf;
import com.example.demo.service.TransactionInterf;

@RestController
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    public DemoController() {
        logger.info("Creating Object: .."+this);
    }

    @Autowired
    TransactionInterf transactionInterf;

    //Field Injection
//    @Autowired
//    Demo demo;

    Demo demo;

    //Constructor Injection
    @Autowired
    public DemoController(Demo demo){
        this.demo = demo;
        logger.info("Demo: {}",demo);
    }

//    public DemoController(int a){
//        logger.info(String.valueOf(a));
//    }

    //com.example.demo.model.Demo@2fa7ae9

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("CardPayment")
    PaymentInterf paymentInterf;

    @GetMapping("/demo")
    public Demo getDemo(){
//        Demo demo = new Demo();
        logger.info("Demo object in getDemo() of DemoController is :"+demo);
        paymentInterf.pay();
        return demo;
    }

    //com.example.demo.model.Demo@182b435b - this is created by spring
    //com.example.demo.model.Demo@1ce79b62
    //com.example.demo.model.Demo@30d201f2

    //com.example.demo.model.Demo@51c929ae
    //com.example.demo.model.Demo@51c929ae

    //prototype bean
    //DemoController       : Demo: com.example.demo.model.Demo@3228d990
    //DemoController2           : ..com.example.demo.model.Demo@88d6f9b
    ///demo2: com.example.demo.model.Demo@88d6f9b


    @GetMapping("/getTemplate")
    public void getTemplate(){
        logger.info("In DemoController: getTemplate: {}",restTemplate);
    }

}
