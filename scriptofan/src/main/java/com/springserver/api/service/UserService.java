package com.springserver.api.service;

import com.springserver.api.model.User;
import com.springserver.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String email, String password) {
        User u = new User();
        u.setUserName(name);
        u.setUserEmail(email);
        u.setUserPassword(encoder.encode(password));
        return userRepository.save(u);
    }

    public User update(User user, User userRequest, String updatedBy) {
        user.setUpdatedBy(updatedBy);
        user.setUpdateTime(Instant.now());

        user.setUserEmail(userRequest.getUserEmail());
        user.setRole(userRequest.getRole());
        user.setUserPassword(encoder.encode(userRequest.getUserPassword()));
        user.setUserPhonenumber(userRequest.getUserPhonenumber());

        return userRepository.save(user);
    }

    public void delete(User user, String deletedBy) {
        user.setDeletedBy(deletedBy);
        user.setDeleteTime(Instant.now());
        userRepository.save(user);
    }
}
