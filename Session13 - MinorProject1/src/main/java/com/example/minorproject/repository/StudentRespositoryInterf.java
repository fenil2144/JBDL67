package com.example.minorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorproject.enums.AccountStatus;
import com.example.minorproject.models.Student;

public interface StudentRespositoryInterf extends JpaRepository<Student, Integer> {

    Student findByIdAndAccountStatus(int studentId, AccountStatus accountStatus);
}
