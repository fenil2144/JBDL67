package com.example.jpaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.example.jpaDemo.models.Author;
import com.example.jpaDemo.service.AuthorServiceInterf;

@RestController
public class AuthorController {

    @Autowired
    AuthorServiceInterf authorServiceInterf;


    @PostMapping("/addAuthor")
    public ResponseEntity createAuthor(@RequestBody @Valid Author author){
        return new ResponseEntity(authorServiceInterf.createAuthor(author), HttpStatus.CREATED);
    }
}
