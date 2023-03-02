package com.springserver.api.repository;

import com.springserver.api.model.Size;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SizeRepository extends CrudRepository<Size, String> {
    public Optional<Size> findById(String id);
}
