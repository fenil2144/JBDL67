package com.example.springSecurityDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springSecurityDemo.model.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findByEmail(String email);
}
