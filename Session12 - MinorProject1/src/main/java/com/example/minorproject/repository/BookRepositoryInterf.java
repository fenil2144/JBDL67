package com.example.minorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minorproject.models.Book;

@Repository
public interface BookRepositoryInterf extends JpaRepository<Book, Integer> {
}
