package com.springserver.api.repository;

import com.springserver.api.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    public Optional<Category> findById(String id);
}
