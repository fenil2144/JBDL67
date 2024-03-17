package com.example.jpaDemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import com.example.jpaDemo.models.Person;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonRequest {

    @NotBlank(message = "first name can't be empty")
    private String fName;
    private String lName;

    @NotBlank(message = "date of birth can't be empty")
    private String dateOfBirth;

    public Person toPerson(){
        return Person.builder()
                .firstName(fName).lastName(lName).dob(dateOfBirth)
                .build();
    }
}

