package com.example.jpaDemo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jpaDemo.models.Author;
import com.example.jpaDemo.repository.AuthorRepositoryInterf;

public interface AuthorServiceInterf {

public Author createAuthor(Author author);


}
