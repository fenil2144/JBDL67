package com.example.jpaDemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private int id;

    @Column(name = "fName",unique = true)
    private String firstName;
    private String lastName;
    private int age;
    private String dob;

    @Transient
    private boolean isAlive;
}

