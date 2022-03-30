package com.users;

import com.users.model.Users;
import com.users.repository.UsersRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedUsers {
    private static final Logger log = LoggerFactory.getLogger(SeedUsers.class);

    @Bean
    CommandLineRunner initDatabase(UsersRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Users("akshayjr69@gmail.com", "Akshay")));
            log.info("Preloading " + repository.save(new Users("johnDoe44@gmail.com", "John")));
            log.info("Preloading " + repository.save(new Users("janeDoe@gmail.com", "Jane")));
            log.info("Preloading " + repository.save(new Users("ashley55@gmail.com", "Ashley")));
            log.info("Preloading " + repository.save(new Users("mark123@gmail.com", "Mark")));
        };
    }
}
