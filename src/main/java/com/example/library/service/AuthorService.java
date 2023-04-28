package com.example.library.service;

import com.example.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author newAuthor(String name, String surname, Long countryId);

    List<Author> listAll();
    Optional<Author> findById(Long id);

    void deleteById(Long id);

}
