package com.springserver.api.repository;

import com.springserver.api.model.GarmentStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface GarmentStatusRepository extends CrudRepository<GarmentStatus, String> {
    public Optional<GarmentStatus> findById(String id);
}
