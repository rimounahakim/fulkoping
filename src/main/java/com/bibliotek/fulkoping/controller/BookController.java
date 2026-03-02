package com.bibliotek.fulkoping.controller;

import com.bibliotek.fulkoping.model.Book;
import com.bibliotek.fulkoping.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String listBooks(@RequestParam(required = false) String keyword,
                            Model model) {

        List<Book> books;

        if (keyword != null && !keyword.isEmpty()) {
            books = bookRepository
                    .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                            keyword, keyword);
        } else {
            books = bookRepository.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);

        return "books";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable Long id,
                              Model model) {

        Book book = bookRepository.findById(id).orElseThrow();

        model.addAttribute("book", book);

        return "book-details";
    }
}