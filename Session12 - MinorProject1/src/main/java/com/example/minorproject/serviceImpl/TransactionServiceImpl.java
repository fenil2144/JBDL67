package com.example.minorproject.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.minorproject.repository.StudentRespositoryInterf;
import com.example.minorproject.service.TransactionServiceInterf;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {
    @Override
    public String transact(int studentId, int bookId, String transactionType) {
        return null;
    }
}
