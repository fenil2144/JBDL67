package com.example.minorproject.controller;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.example.minorproject.models.MyUser;
import com.example.minorproject.requests.StudentCreateRequest;
import com.example.minorproject.service.StudentServiceInterf;

@RestController
public class StudentController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentServiceInterf studentServiceInterf;

    //CRUD API for Student

    @PostMapping("/register")
    public ResponseEntity saveStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        return new ResponseEntity(studentServiceInterf.save(studentCreateRequest), HttpStatus.CREATED);

    }

    // for studnet role
    @GetMapping("/student")
    public ResponseEntity getStudent() throws Exception {

        logger.info("In StudentController:getStudent");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();

        if(myUser.getStudent() == null) {
            throw new Exception("User is not a student.");
        }

        ResponseEntity responseEntity = new ResponseEntity(studentServiceInterf.findById(myUser.getStudent().getId()), HttpStatus.OK);
        logger.info("In StudentController:getStudent");
        return responseEntity;
    }


    // Only for admin role
    @GetMapping("/student_for_admin")
    public ResponseEntity getStudentForAdmin(@RequestParam("studentIds") String studentIds) throws Exception {

        logger.info("In StudentController:getStudent");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();

        if(myUser.getAdmin() == null) {
            throw new Exception("User is not a admin.");
        }

        String[] studentArray = studentIds.split(",");
        List<Integer> stdList = Arrays.stream(studentArray).map(x -> Integer.parseInt(x)).collect(Collectors.toList());

        ResponseEntity responseEntity = new ResponseEntity(studentServiceInterf.findAllById(stdList), HttpStatus.OK);
        logger.info("In StudentController:getStudent");
        return responseEntity;
    }


}
