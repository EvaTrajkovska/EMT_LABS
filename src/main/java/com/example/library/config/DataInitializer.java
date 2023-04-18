package com.example.library.config;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Category;
import com.example.library.model.Country;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataInitializer {

   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;
   private final CountryRepository countryRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

   // private Category randomizeEventType(int i) {
   //     if (i % 3 == 0) return Category.CLASSICS;
   //     else if (i % 3 == 1) return Category.BIOGRAPHY;
   //     return Category.FANTASY; }

        @PostConstruct
        public void init() {
            if (countryRepository.count() != 0 || authorRepository.count() != 0 || bookRepository.count() != 0) {
                return;
            }

            for (int i = 1; i < 16; i++) {
                create(i);
            }
        }

        private void create(int i) {
            Country c = new Country();
            c.setName(String.format("Country %d", i));
            c.setContinent(String.format("Continent %d", i));
            countryRepository.save(c);

            Author a = new Author();
            a.setName(String.format("Name %d", i));
            a.setSurname(String.format("Surname %d", i));
            a.setCountry(c);
            authorRepository.save(a);

            Book b = new Book();
            b.setName(String.format("Name %d", i));
            b.setCategory(Category.values()[i % Category.values().length]);
            b.setAuthor(a);
            b.setAvailableCopies(i);
            bookRepository.save(b);
        }
}
