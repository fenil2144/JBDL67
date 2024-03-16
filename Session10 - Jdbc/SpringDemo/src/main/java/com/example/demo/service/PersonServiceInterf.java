package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.CreatePersonRequest;
import com.example.demo.model.Person;

public interface PersonServiceInterf {

    void createPerson(CreatePersonRequest createPersonRequest);

    Person getPersonById(int id);

    List<Person> getAllPersons();

    Person updatePerson(CreatePersonRequest createPersonRequest, int personId);

    void deletePerson(int personId);
}
