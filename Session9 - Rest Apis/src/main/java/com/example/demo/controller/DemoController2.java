package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Demo;

@RestController
public class DemoController2 {

    @Autowired
    Demo demo;

    private static Logger logger = LoggerFactory.getLogger(DemoController2.class);

    @GetMapping("/demo2")
    public Demo getDemo(){
//        Demo demo = new Demo();
        logger.info("Demo object in getDemo() of DemoController2 is :"+demo);
        return demo;
    }

    //com.example.demo.model.Demo@2fa7ae9 - CREATED BY SPRING
    //com.example.demo.model.Demo@1381571c - demo controller1
    //com.example.demo.model.Demo@669430a2 - demo controller2

    //After Autowiring
    //com.example.demo.model.Demo@4d0402b - created by spring
    //com.example.demo.model.Demo@4d0402b - In DemoController
    //com.example.demo.model.Demo@4d0402b - In DemoController2
}
