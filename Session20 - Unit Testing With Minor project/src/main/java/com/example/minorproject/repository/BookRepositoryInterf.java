package com.example.minorproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minorproject.enums.Genre;
import com.example.minorproject.models.Book;

@Repository
public interface BookRepositoryInterf extends JpaRepository<Book, Integer> {

    List<Book> findByName(String name);
    List<Book> findByAuthor_name(String name);
    List<Book> findByGenre(Genre genre);
    List<Book> findByCost(int value);


}
