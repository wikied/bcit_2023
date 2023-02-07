package com.springserver.api.repository;

import com.springserver.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findById(String id);

    Optional<User> findByUserName(String name);
}
