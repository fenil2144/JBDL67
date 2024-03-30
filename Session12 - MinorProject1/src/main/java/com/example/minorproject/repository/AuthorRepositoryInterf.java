package com.example.minorproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minorproject.models.Author;

@Repository
public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);
}
