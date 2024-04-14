package com.example.minorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minorproject.models.MyUser;

@Repository
public interface MyUserRepositoryInterf extends JpaRepository<MyUser, Integer> {

    MyUser findByUsername(String username);
}
