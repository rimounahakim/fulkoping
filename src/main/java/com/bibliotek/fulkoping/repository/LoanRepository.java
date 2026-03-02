package com.bibliotek.fulkoping.repository;

import com.bibliotek.fulkoping.model.Loan;
import com.bibliotek.fulkoping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Hämta alla lån för en användare
    List<Loan> findByUser(User user);

    // Hämta endast aktiva lån
    List<Loan> findByUserAndReturnedFalse(User user);
}