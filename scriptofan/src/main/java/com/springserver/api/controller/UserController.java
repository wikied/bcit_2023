package com.springserver.api.controller;

import com.springserver.api.model.User;
import com.springserver.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public void createUser(String name, String email, String password) {
        User u = new User();
        u.setUserName(name);
        u.setUserEmail(email);
        u.setUserPassword(encoder.encode(password));
        userRepository.save(u);
    }
}
