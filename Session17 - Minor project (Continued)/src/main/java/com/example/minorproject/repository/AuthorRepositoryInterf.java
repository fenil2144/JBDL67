package com.example.minorproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.minorproject.models.Author;

@Repository
public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);

    //JPQL: Using JPA Query Language
    @Query("select a from Author a where a.email= ?1")
    public Author getAuthorByEmailId(String email);

    //Native Queries: Specific to database
    @Query(value = "select a from author a where a.email= ?1",nativeQuery = true)
    public Author getAuthorByEmailUsingNative(String email);

    //find the authors above the age of 30, living on India and name starting with p
    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(int age,
                                                                        String country, String prefix);


}
