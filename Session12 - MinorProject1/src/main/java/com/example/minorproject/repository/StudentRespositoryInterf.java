package com.example.minorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorproject.models.Student;

public interface StudentRespositoryInterf extends JpaRepository<Student, Integer> {
}
