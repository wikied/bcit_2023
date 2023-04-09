package com.springserver.api.repository;

import org.springframework.data.repository.CrudRepository;
import com.springserver.api.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderRepository extends CrudRepository<Gender, String> {
    public Optional<Gender> findById(String id);
}
