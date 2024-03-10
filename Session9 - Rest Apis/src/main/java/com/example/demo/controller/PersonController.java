package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.example.demo.dtos.CreatePersonRequest;
import com.example.demo.dtos.ErrorResponse;
import com.example.demo.model.Person;
import com.example.demo.service.PaymentInterf;

@RestController
public class PersonController {

    @Autowired
    PaymentInterf paymentInterf;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

//    @PostMapping("/addPerson")
//    public void createPerson(@RequestBody Person person){
//        //Logic to save person
//    }

    @PostMapping("/addPerson")
    public ResponseEntity createPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest){
        logger.info("createPersonRequest: {}",createPersonRequest);

//        if(createPersonRequest.getFName() == null ||  createPersonRequest.getFName().isEmpty()){
//            logger.error("person's first name is empty");
//            return;
//        }
//
//        if(createPersonRequest.getDateOfBirth() == null ||  createPersonRequest.getDateOfBirth().isEmpty()){
//            logger.error("person's date of birth is empty");
//            return;
//        }


        //Logic to save person
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("sample_header","Sample Header for New Person object");
        return new ResponseEntity(createPersonRequest, headers, HttpStatus.CREATED);
    }




}
