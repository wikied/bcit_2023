package com.springserver.api.repository;

import com.springserver.api.model.Style;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends CrudRepository<Style, String> {
    public Optional<Style> findById(String id);
}
