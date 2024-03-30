package com.example.minorproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minorproject.models.Transaction;

@Repository
public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer> {
}
