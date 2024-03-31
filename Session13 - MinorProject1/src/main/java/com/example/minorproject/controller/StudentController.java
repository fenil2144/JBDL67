package com.example.minorproject.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorproject.requests.StudentCreateRequest;
import com.example.minorproject.service.StudentServiceInterf;

@RestController
public class StudentController {

    @Autowired
    StudentServiceInterf studentServiceInterf;

    //CRUD API for Student

    @PostMapping("/student")
    public ResponseEntity saveStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        return new ResponseEntity(studentServiceInterf.save(studentCreateRequest), HttpStatus.CREATED);

    }


}
