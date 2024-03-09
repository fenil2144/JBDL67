package com.example.demo.controller;

import java.util.HashMap;

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


}
