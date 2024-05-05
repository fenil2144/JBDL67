package com.example.minorproject.service;

import java.util.List;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.models.Student;
import com.example.minorproject.requests.StudentCreateRequest;

public interface StudentServiceInterf {

    Student save(StudentCreateRequest studentCreateRequest);
    Student saveFromStudent(Student student);
    Student findById(int studentId);

    List<Student> findAllById(List<Integer> studentId);

    Student findByIdAndAccountStatus(int studentId, AccountStatus accountStatus);
}
