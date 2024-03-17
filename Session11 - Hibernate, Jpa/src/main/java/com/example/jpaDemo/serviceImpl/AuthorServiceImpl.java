package com.example.jpaDemo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpaDemo.models.Author;
import com.example.jpaDemo.repository.AuthorRepositoryInterf;
import com.example.jpaDemo.service.AuthorServiceInterf;

@Service
public class AuthorServiceImpl implements AuthorServiceInterf {

    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;

    @Override
    public Author createAuthor(Author author) {
        return authorRepositoryInterf.save(author);
    }
}
