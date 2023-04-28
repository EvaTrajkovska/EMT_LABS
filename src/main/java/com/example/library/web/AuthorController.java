package com.example.library.web;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author>findAll(){
        return this.authorService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author>findById(@PathVariable Long id){
        return this.authorService.findById(id).map(author -> ResponseEntity.ok().body(author)).
                orElseGet(()->ResponseEntity.notFound().build());
    }
}
