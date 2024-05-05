package com.example.minorproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minorproject.enums.TransactionType;
import com.example.minorproject.models.Book;
import com.example.minorproject.models.Student;
import com.example.minorproject.models.Transaction;

@Repository
public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer> {

    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student, TransactionType transactionType);
}
