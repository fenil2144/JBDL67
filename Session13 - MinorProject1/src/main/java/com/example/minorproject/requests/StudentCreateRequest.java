package com.example.minorproject.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.models.Student;

@Data
@Builder
@AllArgsConstructor
public class StudentCreateRequest {

    @NotBlank
    private String name;
    private String email;

    @NotBlank
    private String contact;

    private String address;

    public Student toStudent() {
        return Student.builder()
                .address(address)
                .contact(contact)
                .email(email)
                .name(name)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

}
