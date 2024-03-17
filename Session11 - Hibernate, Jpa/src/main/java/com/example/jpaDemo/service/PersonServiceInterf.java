package com.example.jpaDemo.service;

import java.util.List;

import com.example.jpaDemo.dtos.CreatePersonRequest;
import com.example.jpaDemo.models.Person;

public interface PersonServiceInterf {

    Person createPerson(CreatePersonRequest createPersonRequest);

    Person getPersonById(int id);

    List<Person> getAllPersons();

    Person updatePerson(CreatePersonRequest createPersonRequest, int personId);

    void deletePerson(int personId);

    Person getPersonByName(String name);
}
