package com.example.jpaDemo.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpaDemo.dtos.CreatePersonRequest;
import com.example.jpaDemo.exceptions.BadPersonRequestException;
import com.example.jpaDemo.models.Person;
import com.example.jpaDemo.repository.PersonRepositoryInterf;
import com.example.jpaDemo.service.PersonServiceInterf;

@Service
public class PersonServiceImpl implements PersonServiceInterf {

    @Autowired
    PersonRepositoryInterf personRepositoryInterf;


    @Override
    public Person createPerson(CreatePersonRequest createPersonRequest) {
        Person person = createPersonRequest.toPerson();

        if(person.getAge() == 0){
            person.setAge(calculateAgeFromDOB(person.getDob()));
        }
        return personRepositoryInterf.save(person);
    }

    @Override
    public Person getPersonById(int personId) {
        return personRepositoryInterf.findById(personId).orElseThrow(
                () -> new BadPersonRequestException("Person with id="+personId+" is not present")
        );
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepositoryInterf.findAll();
    }

    @Override
    public Person updatePerson(CreatePersonRequest createPersonRequest, int personId) {

        Person person = personRepositoryInterf.findById(personId).orElseThrow(
                () -> new BadPersonRequestException("Person with id="+personId+" is not present"));

        if(person==null){
            throw new BadPersonRequestException("Person with id="+personId+" is not present");
        }

        person = createPersonRequest.toPerson();
        person.setId(personId);
        person.setAge(calculateAgeFromDOB(person.getDob()));
        return personRepositoryInterf.save(person);
    }

    @Override
    public void deletePerson(int personId) {
        personRepositoryInterf.deleteById(personId);
    }

    @Override
    public Person getPersonByName(String name) {
        return personRepositoryInterf.findByFirstName(name).get();
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
