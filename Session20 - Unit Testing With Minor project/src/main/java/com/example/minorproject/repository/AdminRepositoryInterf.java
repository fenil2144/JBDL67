package com.example.minorproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minorproject.models.Admin;

@Repository
public interface AdminRepositoryInterf extends JpaRepository<Admin, Integer> {
}
