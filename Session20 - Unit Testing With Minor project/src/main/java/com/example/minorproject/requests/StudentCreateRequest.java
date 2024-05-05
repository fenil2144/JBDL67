package com.example.minorproject.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.catalina.User;

import javax.validation.constraints.NotBlank;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.models.Student;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {

    @NotBlank
    private String name;
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String authority;

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

    public UserCreateRequest toUser(){
        return UserCreateRequest.builder()
                .student(toStudent())
                .username(username).password(password)
                .build();
    }

}
