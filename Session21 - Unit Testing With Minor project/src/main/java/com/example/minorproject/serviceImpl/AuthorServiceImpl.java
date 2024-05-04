package com.example.minorproject.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorproject.models.Author;
import com.example.minorproject.repository.AuthorRepositoryInterf;
import com.example.minorproject.service.AuthorServiceInterf;

@Service
public class AuthorServiceImpl implements AuthorServiceInterf {

    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;
    @Override
    public Author saveAuthor(Author author) {
        return authorRepositoryInterf.save(author);
    }

    @Override
    public Author findByEmail(String email) {
        return authorRepositoryInterf.findByEmail(email);
    }
}
