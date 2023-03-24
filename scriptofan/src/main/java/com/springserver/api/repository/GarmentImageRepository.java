package com.springserver.api.repository;

import com.springserver.api.model.GarmentImage;
import com.springserver.api.model.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface GarmentImageRepository extends CrudRepository<GarmentImage, String>{
    public Optional<GarmentImage> findById(String id);
}
