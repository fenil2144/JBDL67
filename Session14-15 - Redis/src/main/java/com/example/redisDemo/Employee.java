package com.example.redisDemo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Employee implements Serializable {

    private long id;
    private String name;
    private int age;
    private double salary;

}
