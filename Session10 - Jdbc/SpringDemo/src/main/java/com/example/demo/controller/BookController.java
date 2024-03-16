package com.example.demo.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;

@RestController
public class BookController {

    // insertBook - Post - RequestBody
    // updateBook - Put - RequestBody
    // GetBookDetails - Get - pathVariable
    // deleteBook - Delete - requestParam/pathVariable
    // getAllBooks - Get

    private HashMap<Integer,Book>  bookHashMap = new HashMap<Integer, Book>();

//    @GetMapping({"/book/{bookId}","/book"})
//    public Book getBookById(@PathVariable Optional<Integer> bookId){
//        if(bookId.isPresent())
//            //continue
//        else
//            //continue
//    }

    @GetMapping("/book")
    public void getBook(){

    }

    @PostMapping("/book")
    public void getBookDetails(){

    }

}
