package com.example.jpaDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jpaDemo.models.Person;

@Repository
public interface PersonRepositoryInterf extends JpaRepository<Person, Integer> {

    //Custom Jpa methods
    Optional<Person> findByFirstName(String firstName);

    Optional<Person> findByFirstNameAndLastNameOrAge(String firstName, String lastName, int age);

    Optional<Person> findByAgeGreaterThan(int age);

    //Writing Jpql queries
    @Query("select p from Person p where id=:personId")
    Person getPerson(int personId);

    // writing Native Queries
    @Query(value = "select * from person where id=:personId", nativeQuery = true)
    Person getPersonNative(int personId);


}
