package com.bibliotek.fulkoping.model;

import jakarta.persistence.*;

/*
 Denna klass representerar en användare i systemet.
 En användare kan vara USER eller ADMIN.
*/
@Entity
@Table(name = "users") // Viktigt eftersom USER är reserverat ord i MySQL
public class User {

    // Primärnyckel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Användarnamn (måste vara unikt)
    @Column(unique = true)
    private String username;

    // Lösenord (kommer att sparas som hash)
    private String password;

    // Roll: USER eller ADMIN
    private String role;

    // Tom konstruktor krävs av JPA
    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters och setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /*
     Här sätter vi lösenordet (kommer senare hash:as med BCrypt)
    */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}