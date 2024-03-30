package com.example.minorproject.service;


import com.example.minorproject.models.Book;
import com.example.minorproject.requests.BookCreateRequest;

public interface BookServiceInterf {
    Book saveBook(BookCreateRequest bookCreateRequest);
}
