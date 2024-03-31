package com.example.minorproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;


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
