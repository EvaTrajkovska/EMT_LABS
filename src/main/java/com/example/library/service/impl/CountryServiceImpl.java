package com.example.library.service.impl;

import com.example.library.model.Country;
import com.example.library.repository.CountryRepository;
import com.example.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public Country newCountry(String name, String continent) {
        return countryRepository.save(new Country(name,continent));
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
       Country c=countryRepository.findById(id).get();
       countryRepository.delete(c);
    }
}
