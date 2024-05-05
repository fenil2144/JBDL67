package com.example.minorproject.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompositeKeyModel implements Serializable {

    @Id
    private int id;

    @Id
    private String name;

    @CreationTimestamp
    private Date createdOn;
}
