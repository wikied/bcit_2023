package com.springserver.api.repository;

import com.springserver.api.model.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, String> {
    public Optional<SubCategory> findById(String id);
}
