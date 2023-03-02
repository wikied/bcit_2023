package com.springserver.api.controller;

import com.springserver.api.model.User;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.UserRepository;
import com.springserver.api.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    @PermitAll
    public @ResponseBody User addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String password) {

        return userService.createUser(name, email, password);
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public User updateUser(Authentication authentication, @PathVariable String id, @RequestBody User userRequest) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        return userService.update(user.get(), userRequest, authentication.getName());
    }

    @GetMapping("/{id}")
    public @ResponseBody User findUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        return userRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(Authentication authentication, @PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        userService.delete(user.get(), authentication.getName());
        return ResponseEntity.ok().build();
    }
}
