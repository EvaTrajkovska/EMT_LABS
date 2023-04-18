package com.example.library.service.impl;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CountryRepository;
import com.example.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> newBook(BookDto bookDto) {
        Author author= authorRepository.findById(bookDto.getAuthor().getId()).get();
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(), bookDto.getCategory(),author, bookDto.getAvailableCopies())));

    }


    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book b = bookRepository.findById(id).get();
        Author author= authorRepository.findById(bookDto.getAuthor().getId()).get();
        b.setName(bookDto.getName());
        b.setAuthor(author);
        b.setCategory(bookDto.getCategory());
        b.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(b));

    }

    @Override
    public void deleteById(Long id) {
        Book b = bookRepository.findById(id).get();
        this.bookRepository.delete(b);
    }

    @Override
    public Page<Book> FindAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public void taken(Long id) {
        Book book=bookRepository.findById(id).get();
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
    }
}
