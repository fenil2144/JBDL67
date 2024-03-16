package com.example.demo.repository;


import java.sql.SQLException;

import com.example.demo.model.Person;

public interface PersonRepositoryInterf {

    void createPersonStatic(Person person);

    void createPerson(Person person);

    Person getPersonById(int id);
}
