package com.example.jpaDemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person_tab")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fName",unique = true)
    private String firstName;

    @Column(name = "lName",unique = true)
    private String lastName;
    private int age;
    private String dob;
    private String temp1;

    @Transient
    private boolean isAlive;

//    AUTO:
//    Create table (id int primary key, first_name )
//    insert into person_tab (..., id) values (..., ?)

//    IDENTITY:
//    create tabke (id int primary key auto_increment,)
//    insert into person_tab (...) values (...)
}

