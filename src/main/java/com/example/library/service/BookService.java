package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

     List<Book> findAll();

     Optional<Book> findById(Long id);
     Optional<Book> newBook(BookDto bookDto);
     Optional<Book> edit(Long id, BookDto bookDto);
     void deleteById(Long id);

     Page<Book>FindAllWithPagination(Pageable pageable);

     void taken(Long id);


}
