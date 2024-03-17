package com.example.jpaDemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.example.jpaDemo.dtos.CreatePersonRequest;
import com.example.jpaDemo.models.Person;
import com.example.jpaDemo.service.PersonServiceInterf;

@RestController
public class PersonController {

    @Autowired
    PersonServiceInterf personServiceInterf;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);


    @PostMapping("/addPerson")
    public ResponseEntity createPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest){
        logger.info("createPersonRequest: {}",createPersonRequest);

        Person savedPerson = personServiceInterf.createPerson(createPersonRequest);

        //Logic to save person
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("sample_header","Sample Header for New Person object");
        return new ResponseEntity(savedPerson, headers, HttpStatus.CREATED);
    }

    @GetMapping("/getPerson")
    public Person getPersonbyId(@RequestParam("id") int id){
        return personServiceInterf.getPersonById(id);
    }

    @GetMapping("/getAllPersons")
    public List<Person> getAllPersons(){
        return personServiceInterf.getAllPersons();
    }

    @PutMapping("/{personId}")
    public Person updatePerson(@RequestBody @Valid CreatePersonRequest createPersonRequest, @PathVariable("personId") int personId){
        return personServiceInterf.updatePerson(createPersonRequest,personId);
    }

    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable("personId") int personId){
        personServiceInterf.deletePerson(personId);
    }

    @GetMapping("/getPersonByName")
    public Person getPersonbyId(@RequestParam("name") String name){
        return personServiceInterf.getPersonByName(name);
    }
}
