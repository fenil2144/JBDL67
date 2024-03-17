package com.example.jpaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaDemo.models.Author;

@Repository
public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {
}
