
package com.springserver.api.repository;

import com.springserver.api.model.Garment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GarmentRepository extends CrudRepository<Garment, String> {
    public Optional<Garment> findById(String id);
}