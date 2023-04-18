package com.example.library.model.dto;

import com.example.library.model.Author;
import com.example.library.model.Category;
import lombok.Data;

@Data
public class BookDto {
    private String name;
    private Category category;
    private Integer availableCopies;
    private Author author;


    public BookDto() {
    }

    public BookDto(String name, Category category, Integer availableCopies, Author author) {
        this.name = name;
        this.category = category;
        this.availableCopies = availableCopies;
        this.author = author;
    }
}
