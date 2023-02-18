package com.springserver.api.controller;

import com.springserver.api.model.*;
import com.springserver.api.repository.GarmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Instant;

@RestController
public class GarmentController {
    @Autowired
    private GarmentRepository garmentRepository;

    //get all garments REST API
    @GetMapping("/garment/all")
    public @ResponseBody Iterable<Garment> getAllGarments() {
        return garmentRepository.findAll();
    }

    //get garment by id REST API
    @GetMapping("/garment/get/{id}")
    public @ResponseBody Category getGarment (@PathVariable String id) {
        return garmentRepository.findById(id).get();
    }

    //build create garment REST API
    @PostMapping("/garment/create")
    public @ResponseBody String createGarment(@RequestParam String id, @RequestParam Category category, @RequestParam Seller seller, @RequestParam GarmentImage garmentImage, @RequestParam GarmentStatus garmentStatus,
                                              @RequestParam(required=false) String description, @RequestParam(required = false) String material, @RequestParam(required = false) String defects, @RequestParam(required = false) Integer price,
                                              @RequestParam(required=false) BigDecimal co2Saved, @RequestParam(required=false) Integer garmentRating, @RequestParam(required = false) String details, @RequestParam(required = false) Instant createTime,
                                              @RequestParam(required=false) String createdBy) {
        Garment createGarment = new Garment();
        createGarment.setId(id);
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
        createGarment.setDefects(defects);
        createGarment.setDetails(details);
        createGarment.setCreateTime(createTime);
        createGarment.setCreatedBy(createdBy);

        return "Garment has been successfully created!";
    }
}

