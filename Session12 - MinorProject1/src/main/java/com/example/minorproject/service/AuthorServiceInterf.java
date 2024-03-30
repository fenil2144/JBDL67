package com.example.minorproject.service;

import com.example.minorproject.models.Author;

public interface AuthorServiceInterf {

    public Author saveAuthor(Author author);

    public Author findByEmail(String email);
}
