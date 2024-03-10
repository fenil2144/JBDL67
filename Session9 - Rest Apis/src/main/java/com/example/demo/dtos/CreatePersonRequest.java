package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonRequest {

    @NotBlank(message = "first name can't be empty")
    private String fName;
    private String lName;

    @NotBlank(message = "date of birth can't be empty")
    private String dateOfBirth;
}
