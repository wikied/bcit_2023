package com.springserver.repository;


import org.springframework.data.repository.CrudRepository;

import com.springserver.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findUsersBy(String id);
}
