package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CreatePersonRequest;
import com.example.demo.exception.BadPersonRequestException;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepositoryInterf;
import com.example.demo.service.PersonServiceInterf;

@Service
public class PersonImpl implements PersonServiceInterf {

    @Autowired
    PersonRepositoryInterf personRepositoryInterf;


    @Override
    public void createPerson(CreatePersonRequest createPersonRequest) {
        Person person = createPersonRequest.toPerson();

        if(person.getAge() == 0){
            person.setAge(calculateAgeFromDOB(person.getDob()));
        }
        personRepositoryInterf.createPerson(person);
    }

    @Override
    public Person getPersonById(int id) {

        return personRepositoryInterf.getPersonById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepositoryInterf.getAllPersons();
    }

    @Override
    public Person updatePerson(CreatePersonRequest createPersonRequest, int personId) {

        Person person = personRepositoryInterf.getPersonById(personId);
        if(person==null){
            throw new BadPersonRequestException("Person with id="+personId+" is not present");
        }

        person = createPersonRequest.toPerson();
        person.setId(personId);
        person.setAge(calculateAgeFromDOB(person.getDob()));
        personRepositoryInterf.updatePerson(person);
        return null;
    }

    @Override
    public void deletePerson(int personId) {

    }

    private Integer calculateAgeFromDOB(String dob) {
        if(dob == null){
            return null;
        }
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dobDate,currentDate).getYears();
    }
}
