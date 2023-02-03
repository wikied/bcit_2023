package com.springserver.controller;

import com.springserver.model.User;
import com.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String password) {

        User u = new User();
        u.setUserName(name);
        u.setUserEmail(email);
        u.setUserPassword(password);
        userRepository.save(u);
        return "Saved";
    }

    @GetMapping("/user/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/get/{id}")
    public @ResponseBody User findUserById(@PathVariable String id) {
        return userRepository.findUsersBy(id);
    }

    @GetMapping("/user/test")
    public String test() {
        return "This is test";
    }
}
