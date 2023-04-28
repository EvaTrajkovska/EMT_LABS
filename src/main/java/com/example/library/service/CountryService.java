package com.example.library.service;

import com.example.library.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country newCountry(String name, String continent);
    List<Country>listAll();
    Optional<Country> findById(Long id);

    void deleteById(Long id);
}
