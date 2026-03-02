package com.bibliotek.fulkoping.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    public Loan() {}

    public Loan(Book book, User user, LocalDate loanDate, LocalDate dueDate) {
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public Long getId() { return id; }

    public Book getBook() { return book; }

    public User getUser() { return user; }

    public LocalDate getLoanDate() { return loanDate; }

    public LocalDate getDueDate() { return dueDate; }

    public boolean isReturned() { return returned; }

    public void setReturned(boolean returned) { this.returned = returned; }
}