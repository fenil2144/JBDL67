package com.example.minorproject.response;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


import com.example.minorproject.enums.Genre;
import com.example.minorproject.models.Author;
import com.example.minorproject.models.Student;
import com.example.minorproject.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchResponse {

    private int id;
    private String name;
    private int cost;
    private Genre genre;
    @JsonIgnoreProperties({"book"})
    private Author author;
    private Student student;
    private List<Transaction> transactionList;
    private Date createdOn;
    private Date updatedOn;
}
