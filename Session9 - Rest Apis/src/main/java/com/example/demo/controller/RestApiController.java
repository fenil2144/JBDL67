package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.CustomConfig;
import com.example.demo.model.Demo;

@Controller
@RequestMapping("/v1")
public class RestApiController {
    private static Logger logger = LoggerFactory.getLogger(RestApiController.class);


//    Get, Post, put, delete, patch

    @GetMapping("/requestParam")
    public void requestParamMethod(@RequestParam String name,@RequestParam(required = false, defaultValue = "0") int age){
        logger.info("Request param received with name:{} and age:{}",name,age);
    }

    @GetMapping("/pathVariable/{id}/city/{cityId}")
    public void pathVariableMethod(@PathVariable int id, @PathVariable int cityId){
        logger.info("Path Variable received with id:{} and cityId:{}",id,cityId);
    }

    @PostMapping("/requestBody")
    @ResponseBody
    public Demo requestBodyMethod(@RequestBody Demo demoObj){
        logger.info("Request Body received with demoObj:{} ",demoObj);
        return demoObj;
    }


}
