package com.bibliotek.fulkoping.repository;

import com.bibliotek.fulkoping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 Repository för användare.
 Här kan vi hämta användare från databasen.
*/
public interface UserRepository extends JpaRepository<User, Long> {

    // Söka användare baserat på username
    Optional<User> findByUsername(String username);
}