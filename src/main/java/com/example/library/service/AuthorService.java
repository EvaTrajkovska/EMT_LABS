package com.example.library.service;

import com.example.library.model.Author;

import java.util.List;

public interface AuthorService {
    Author newAuthor(String name, String surname, Long countryId);

    List<Author> listAll();
    void deleteById(Long id);

}
