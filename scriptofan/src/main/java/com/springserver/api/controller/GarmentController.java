package com.springserver.api.controller;

import com.springserver.api.model.Garment;
import com.springserver.api.repository.GarmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
public class GarmentController {
    @Autowired
    private GarmentRepository garmentRepository;

    @PostMapping("garment/{id}/edit")
    public @ResponseBody String editGarment (@PathVariable String id, @RequestParam  String description, @RequestParam String material, @RequestParam String defects, @RequestParam Integer price, @RequestParam BigDecimal co2Saved, @RequestParam Integer garmentRating, @RequestParam String details, @RequestParam Instant updateTime , @RequestParam String updatedBy) {
        Garment garmentID = garmentRepository.findById(id).get();
        garmentID.setDescription(description);
        garmentID.setMaterial(material);
        garmentID.setDefects(defects);
        garmentID.setPrice(price);
        garmentID.setCo2Saved(co2Saved);
        garmentID.setDetails(details);
        garmentID.setUpdateTime(updateTime);
        garmentID.setUpdatedBy(updatedBy);
        return "Saved";
    }

    @PostMapping("garment/{id}/delete")
    public @ResponseBody String deleteGarment (@PathVariable String id, @RequestParam Instant deleteTime , @RequestParam String deletedBy) {
        Garment garmentId = garmentRepository.findById(id).get();
        garmentId.setDeleteTime(deleteTime);
        garmentId.setDeletedBy(deletedBy);
        return "Deleted";
    }
}
