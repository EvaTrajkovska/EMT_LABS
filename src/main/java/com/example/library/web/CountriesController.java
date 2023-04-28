package com.example.library.web;

import com.example.library.model.Country;
import com.example.library.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountriesController {
    private final CountryService countryService;


    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll(){
        return this.countryService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return this.countryService.findById(id).map(country -> ResponseEntity.ok().body(country)).
                orElseGet(()->ResponseEntity.notFound().build());
    }
}
