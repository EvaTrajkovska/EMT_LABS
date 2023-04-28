package com.example.library.web;

import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import com.example.library.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/pagination")
    public List<Book> findAllWithPagination(Pageable pageable) {
        return this.bookService.FindAllWithPagination(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit (@PathVariable Long id,@RequestBody BookDto bookDto){
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/taken/{id}")
    public ResponseEntity<Book> taken (@PathVariable Long id){
        if(this.bookService.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }else{
            bookService.taken(id);
             return ResponseEntity.ok().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save (@RequestBody BookDto bookDto){
        return this.bookService.newBook(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty())
          return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
