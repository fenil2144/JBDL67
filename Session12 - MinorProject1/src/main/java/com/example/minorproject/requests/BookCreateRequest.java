package com.example.minorproject.requests;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.minorproject.enums.Genre;
import com.example.minorproject.models.Author;
import com.example.minorproject.models.Book;
import com.example.minorproject.models.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank
    private String name;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    @NotNull
    private Author author;

    public Book toBook(){
        return Book.builder().cost(this.cost)
                .genre(this.genre)
                .name(this.name).author(this.author)
                .build();
    }
}
