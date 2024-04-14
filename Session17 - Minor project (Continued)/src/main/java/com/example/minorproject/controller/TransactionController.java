package com.example.minorproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorproject.models.MyUser;
import com.example.minorproject.service.TransactionServiceInterf;

@RestController
public class TransactionController {

    @Autowired
    TransactionServiceInterf transactionServiceInterf;

    @PostMapping("/transaction/{transactionType}")
    public ResponseEntity transact(@RequestParam("studentId") int studentId,
                                   @RequestParam("bookId") int bookId, @PathVariable("transactionType") String transactionType) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();

        if(myUser.getStudent() == null) {
            throw new Exception("User is not a student.");
        }

        return new ResponseEntity(transactionServiceInterf.transact(myUser.getStudent().getId(), bookId, transactionType), HttpStatus.OK);
    }


}
