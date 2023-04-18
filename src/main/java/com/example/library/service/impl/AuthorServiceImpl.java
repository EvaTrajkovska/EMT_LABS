package com.example.library.service.impl;

import com.example.library.model.Author;
import com.example.library.model.Country;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.CountryRepository;
import com.example.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    public AuthorServiceImpl(CountryRepository countryRepository, AuthorRepository authorRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Author newAuthor(String name, String surname, Long countryId) {
        Country c=countryRepository.findById(countryId).get();
        return authorRepository.save(new Author(name,surname,c));
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }


    @Override
    public void deleteById(Long id) {
        Author a=authorRepository.findById(id).get();
        authorRepository.delete(a);
    }
}
