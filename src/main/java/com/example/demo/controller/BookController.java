package com.example.demo.controller;
import lombok.RequiredArgsConstructor;

import com.example.demo.domain.Book;
import java.util.List;
import com.example.demo.repository.BookRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping(value = "/book")
    public Book save(@RequestBody Book entity) {
        return this.bookRepository.save(entity);
    }

    @GetMapping(value = "/book/test")
    public Book test() {
        Book book = Book.builder().name("Book1").build();
        return this.bookRepository.save(book);
    }

    @GetMapping(value = "/books")
    public List<Book> books() {
        return this.bookRepository.findAll();
    }


}
