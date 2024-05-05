package com.example.minorproject.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.minorproject.enums.Genre;
import com.example.minorproject.models.Author;
import com.example.minorproject.models.Book;

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
