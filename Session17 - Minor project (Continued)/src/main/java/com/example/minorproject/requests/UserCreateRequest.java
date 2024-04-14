package com.example.minorproject.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.minorproject.models.Admin;
import com.example.minorproject.models.Student;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    private String username;
    private String password;
    private String authority;
    private Student student;
    private Admin admin;
}
