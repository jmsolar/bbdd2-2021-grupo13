package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.User;
import com.grupo13.elasticSearch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public Optional<User> findByEmail(@PathVariable String email) {
        return this.userService.findByEmail(email);
    }

    @PostMapping
    public User create(@PathVariable String email, @PathVariable String fullname, @PathVariable String password, @PathVariable Date dayOfBirth) throws ElasticSearchException {
        return this.userService.create(email, fullname, password, dayOfBirth);
    }
}
