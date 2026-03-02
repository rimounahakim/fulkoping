package com.bibliotek.fulkoping.controller;

import com.bibliotek.fulkoping.model.Book;
import com.bibliotek.fulkoping.model.Loan;
import com.bibliotek.fulkoping.model.User;
import com.bibliotek.fulkoping.repository.BookRepository;
import com.bibliotek.fulkoping.repository.LoanRepository;
import com.bibliotek.fulkoping.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanController(LoanRepository loanRepository,
                          BookRepository bookRepository,
                          UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id,
                             Authentication authentication) {

        if (authentication == null) {
            return "redirect:/login";
        }

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow();

        Book book = bookRepository.findById(id).orElseThrow();

        if (book.isAvailable()) {

            Loan loan = new Loan(
                    book,
                    user,
                    LocalDate.now(),
                    LocalDate.now().plusDays(30)
            );

            book.setAvailable(false);

            loanRepository.save(loan);
            bookRepository.save(book);
        }

        return "redirect:/books";
    }

    @GetMapping("/my")
    public String myLoans(Authentication authentication,
                          Model model) {

        if (authentication == null) {
            return "redirect:/login";
        }

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow();

        List<Loan> loans =
                loanRepository.findByUserAndReturnedFalse(user);

        model.addAttribute("loans", loans);

        return "my-loans";
    }

    @GetMapping("/history")
    public String history(Authentication authentication,
                          Model model) {

        if (authentication == null) {
            return "redirect:/login";
        }

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow();

        List<Loan> loans =
                loanRepository.findByUser(user);

        model.addAttribute("loans", loans);

        return "history";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {

        Loan loan = loanRepository.findById(id).orElseThrow();

        loan.setReturned(true);
        loan.getBook().setAvailable(true);

        loanRepository.save(loan);
        bookRepository.save(loan.getBook());

        return "redirect:/loans/my";
    }
}