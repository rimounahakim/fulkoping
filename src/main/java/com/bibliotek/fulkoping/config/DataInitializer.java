package com.bibliotek.fulkoping.config;

import com.bibliotek.fulkoping.model.Book;
import com.bibliotek.fulkoping.model.User;
import com.bibliotek.fulkoping.repository.BookRepository;
import com.bibliotek.fulkoping.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 Denna klass körs automatiskt när applikationen startar.
 Vi använder den för att:
 1. Skapa en admin-användare om den inte finns.
 2. Lägga till testböcker i databasen om inga finns.
*/

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           BookRepository bookRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        /*
         Skapa admin-användare
        */
        if (userRepository.findByUsername("rimouna").isEmpty()) {

            User admin = new User();
            admin.setUsername("rimouna");

            // Lösenordet krypteras (hashas) innan det sparas
            admin.setPassword(passwordEncoder.encode("rimouna1234"));

            admin.setRole("ADMIN");

            userRepository.save(admin);

            System.out.println("Admin-användare skapad.");
        }

        /*
         Lägg till böcker
         Böckerna läggs bara till om databasen är tom.
        */
        if (bookRepository.count() == 0) {

            bookRepository.save(new Book(
                    "Förvandlingen",
                    "Franz Kafka",
                    "En berättelse om Gregor Samsa som förvandlas till en insekt."
            ));

            bookRepository.save(new Book(
                    "Brott och Straff",
                    "Fjodor Dostojevskij",
                    "En psykologisk roman om skuld och moral."
            ));

            bookRepository.save(new Book(
                    "Vita nätter",
                    "Fjodor Dostojevskij",
                    "En romantisk och melankolisk berättelse."
            ));

            bookRepository.save(new Book(
                    "Djurfarmen",
                    "George Orwell",
                    "En politisk satir om makt och korruption."
            ));

            bookRepository.save(new Book(
                    "1984",
                    "George Orwell",
                    "En dystopisk roman om övervakning och totalitarism."
            ));

            bookRepository.save(new Book(
                    "Så talade Zarathustra",
                    "Friedrich Nietzsche",
                    "En filosofisk roman om övermänniskan och moral."
            ));

            System.out.println("Testböcker skapade.");
        }
    }
}