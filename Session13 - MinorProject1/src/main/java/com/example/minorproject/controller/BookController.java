package com.example.minorproject.controller;


import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorproject.enums.BookFilterType;
import com.example.minorproject.models.Book;
import com.example.minorproject.requests.BookCreateRequest;
import com.example.minorproject.response.BookSearchResponse;
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

    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(@RequestParam("filter") BookFilterType bookFilterType,
                                              @RequestParam("value") String value){

        return bookServiceInterf.findBooks(bookFilterType, value).stream()
                .map(Book::toBookSearchResponse)
                .collect(Collectors.toList());
    }
}
