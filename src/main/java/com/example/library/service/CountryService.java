package com.example.library.service;

import com.example.library.model.Country;

import java.util.List;

public interface CountryService {
    Country newCountry(String name, String continent);
    List<Country>listAll();
    void deleteById(Long id);
}
