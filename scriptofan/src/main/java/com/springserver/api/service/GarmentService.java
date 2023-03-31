package com.springserver.api.service;


import com.springserver.api.model.*;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.GarmentRepository;
import com.springserver.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.dnd.DragSourceMotionListener;
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

    public Garment getGarment(String id) {
        return garmentRepository.findById(id).get();
    }

    public Garment createGarment(Garment garment, String createdBy) {
        if (garment == null || garment.getCategory() == null || garment.getSeller() == null || garment.getGarmentImage() == null || garment.getGarmentStatus() == null) {
            return null;
        }
        garment.setCreatedBy(createdBy);
        garment.setCreateTime(Instant.now());
        Garment creategarment = garmentRepository.save(garment);
        return creategarment;
    }


    public Garment editGarment (String id, Garment updatedgarment, String updatedBy) {
        Garment existingGarment = garmentRepository.findById(id).orElse(null);

        if (existingGarment == null) {
            return null;
        }

        existingGarment.setDescription(updatedgarment.getDescription());
        existingGarment.setMaterial(updatedgarment.getMaterial());
        existingGarment.setDefects(updatedgarment.getDefects());
        existingGarment.setPrice(updatedgarment.getPrice());
        existingGarment.setCo2Saved(updatedgarment.getCo2Saved());
        existingGarment.setDetails(updatedgarment.getDetails());
        existingGarment.setGarmentStatus(updatedgarment.getGarmentStatus());
        existingGarment.setUpdateTime(Instant.now());
        existingGarment.setUpdatedBy(updatedBy);
        existingGarment.setUpdateTime(Instant.now());
        existingGarment.setUpdatedBy(updatedBy);


        Garment updatedGarmentSave = garmentRepository.save(existingGarment);

        return updatedGarmentSave;
    }

    public Garment deleteGarment(String id, String deletedBy) {

        Garment garmentToDelete = garmentRepository.findById(id).orElse(null);

        if (garmentToDelete == null) {
            return null;
        }
        garmentToDelete.setDeleteTime(Instant.now());
        garmentToDelete.setDeletedBy(deletedBy);

        Garment deletegarmentSave = garmentRepository.save(garmentToDelete);

        return deletegarmentSave;
    }
}