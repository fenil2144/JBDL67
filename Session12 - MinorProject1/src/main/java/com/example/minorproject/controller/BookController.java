package com.example.minorproject.controller;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorproject.models.Book;
import com.example.minorproject.requests.BookCreateRequest;
import com.example.minorproject.service.BookServiceInterf;

@RestController
public class BookController {


    @Autowired
    BookServiceInterf bookServiceInterf;

    @PostMapping("/book")
    public ResponseEntity saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        Book book = bookServiceInterf.saveBook(bookCreateRequest);
        return new ResponseEntity(book, HttpStatus.CREATED);
    }
}
