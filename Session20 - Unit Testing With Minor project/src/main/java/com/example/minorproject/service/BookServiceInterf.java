package com.example.minorproject.service;


import java.util.List;
import java.util.Optional;

import com.example.minorproject.enums.BookFilterType;
import com.example.minorproject.models.Book;
import com.example.minorproject.requests.BookCreateRequest;

public interface BookServiceInterf {
    Book saveBook(BookCreateRequest bookCreateRequest);

    List<Book> findBooks(BookFilterType bookFilterType, String value);

    public Optional<Book> findById(Integer id);

    Book save(Book book);
}
