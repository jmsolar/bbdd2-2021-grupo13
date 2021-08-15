package com.grupo13.elasticSearch.controllers;

import com.grupo13.elasticSearch.exception.ElasticSearchException;
import com.grupo13.elasticSearch.models.User;
import com.grupo13.elasticSearch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public Optional<User> findByEmail(@PathVariable String email) {
        return this.userService.findByEmail(email);
    }

    @PostMapping
    public User create(@RequestBody final User user) throws ElasticSearchException {
        return this.userService.create(user.getEmail(), user.getFullname(), user.getPassword(), user.getDayOfBirth());
    }

    @GetMapping("/getTopNUsersMorePurchase/{n}")
    public List<User> getTopNUsersMorePurchase(@PathVariable int n) {
        return this.userService.getTopNUsersMorePurchase(n);
    }
}
