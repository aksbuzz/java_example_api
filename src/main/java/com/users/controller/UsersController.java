package com.users.controller;

import java.util.List;

import com.users.model.Users;
import com.users.repository.UsersRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {
    private final UsersRepository repository;

    UsersController(UsersRepository repository) {
        this.repository = repository;
    }

    // get all users
    @GetMapping("/users")
    List<Users> getAllUsers() {
        return repository.findAll();
    }

    // get single user
    @GetMapping("/users/{id}")
    Users getSingleUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    // create new user
    @PostMapping("/users")
    Users addNewUser(@RequestBody Users user) {
        return repository.save(user);
    }

    // update an existing user
    @PutMapping("/users/{id}")
    Users updateUser(@RequestBody Users userToBeUpdated, @PathVariable Long id) {
        return repository.findById(id).map(user -> {
            user.setName(userToBeUpdated.getName());
            user.setEmail(userToBeUpdated.getEmail());
            return repository.save(user);
        }).orElseGet(() -> {
            userToBeUpdated.setId(id);
            return repository.save(userToBeUpdated);
        });
    }

    // delete an existing user
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}