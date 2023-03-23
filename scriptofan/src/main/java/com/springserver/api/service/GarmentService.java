package com.springserver.api.service;


import com.springserver.api.model.*;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.GarmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Service
public class GarmentService {

    @Autowired
    private GarmentRepository garmentRepository;

    public Iterable<Garment> getAllGarments() {
        return garmentRepository.findAll();
    }

    public Garment getGarment(String id) throws ResourceNotFoundException {
        Optional<Garment> optionalGarment = garmentRepository.findById(id);
        if (optionalGarment.isPresent()) {
            return optionalGarment.get();
        } else {
            throw new ResourceNotFoundException("Garment", "Garment id not found", id);
        }
    }

    public Garment createGarment(Category category, Seller seller, GarmentImage garmentImage, GarmentStatus garmentStatus,
                                String description, String material, String defects, Integer price,
                                BigDecimal co2Saved, Integer garmentRating, String details, String createdBy) throws ResourceNotFoundException {

        Garment createGarment = new Garment();
        createGarment.setCategory(category);
        createGarment.setDescription(description);
        createGarment.setSeller(seller);
        createGarment.setGarmentImage(garmentImage);
        createGarment.setGarmentStatus(garmentStatus);
        createGarment.setMaterial(material);
        createGarment.setDefects(defects);
        createGarment.setPrice(price);
        createGarment.setCo2Saved(co2Saved);
        createGarment.setGarmentRating(garmentRating);
        createGarment.setDetails(details);
        createGarment.setCreateTime(Instant.now());
        createGarment.setCreatedBy(createdBy);

        return garmentRepository.save(createGarment);


    }

    public String editGarment(String id, String description, String material, String defects, Integer price, BigDecimal co2Saved, Integer garmentRating, String details, String updatedBy) throws ResourceNotFoundException {
        Optional<Garment> optionalGarment = garmentRepository.findById(id);
        if (optionalGarment.isPresent()) {
            Garment garmentID = optionalGarment.get();
            garmentID.setDescription(description);
            garmentID.setMaterial(material);
            garmentID.setDefects(defects);
            garmentID.setPrice(price);
            garmentID.setCo2Saved(co2Saved);
            garmentID.setDetails(details);
            garmentID.setUpdateTime(Instant.now());
            garmentID.setUpdatedBy(updatedBy);
            garmentRepository.save(garmentID);
            return "Garment has been updated successfully.";
        } else {
            throw new ResourceNotFoundException("Garment", "Garment id not found", id);
        }
    }

    public String deleteGarment(String id, String deletedBy) throws ResourceNotFoundException {
        Optional<Garment> optionalGarment = garmentRepository.findById(id);
        if (optionalGarment.isPresent()) {
            Garment garmentID = optionalGarment.get();
            garmentID.setDeleteTime(Instant.now());
            garmentID.setDeletedBy(deletedBy);
            garmentRepository.save(garmentID);
            return "Garment has been deleted successfully.";
        } else {
            throw new ResourceNotFoundException("Garment with id ", "Id not found.", id);
        }
    }

}