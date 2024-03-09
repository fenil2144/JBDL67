package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.TransactionInterf;

@Configuration
public class TransactionController {

    @Autowired
    TransactionInterf transactionInterf;

}
