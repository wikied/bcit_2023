package com.springserver.api.repository;

import com.springserver.api.model.Colour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColourRepository extends CrudRepository<Colour, String> {
    public Optional<Colour> findById(String id);
}
