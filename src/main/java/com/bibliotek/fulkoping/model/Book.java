package com.bibliotek.fulkoping.model;

import jakarta.persistence.*;

/*
 Denna klass representerar en bok i databasen.
*/
@Entity
public class Book {

    // Primärnyckel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Titel
    private String title;

    // Författare
    private String author;

    // Beskrivning
    @Column(length = 1000)
    private String description;

    // Om boken är tillgänglig
    private boolean available = true;

    public Book() {}

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.available = true;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}