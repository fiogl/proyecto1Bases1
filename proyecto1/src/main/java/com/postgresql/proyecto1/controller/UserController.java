package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.User;
import com.postgresql.proyecto1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @Autowired
    UserRepo repo;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        repo.save(user);
    }
}
