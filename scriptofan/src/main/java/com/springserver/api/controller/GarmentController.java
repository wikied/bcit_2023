package com.springserver.api.controller;

import com.springserver.api.model.*;
import com.springserver.api.repository.GarmentRepository;
import com.springserver.api.service.GarmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Garment;
import com.springserver.api.repository.GarmentRepository;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
public class GarmentController {
    @Autowired
    private GarmentRepository garmentRepository;
    private GarmentService garmentService;

    //get all garments REST API
    @GetMapping("/garment/all")
    public @ResponseBody Iterable<Garment> getAllGarments() {
        return garmentService.getAllGarments();
    }

    //get garment by id REST API
    @GetMapping("/garment/get/{id}")
    public @ResponseBody Garment getGarment (@PathVariable String id) {
        return garmentService.getGarment(id);
    }

    //build create garment REST API
    @PostMapping("/garment/create")
    public @ResponseBody String createGarment(@RequestParam String id, @RequestParam Category category, @RequestParam Seller seller, @RequestParam GarmentImage garmentImage, @RequestParam GarmentStatus garmentStatus,
                                              @RequestParam(required=false) String description, @RequestParam(required = false) String material, @RequestParam(required = false) String defects, @RequestParam(required = false) Integer price,
                                              @RequestParam(required=false) BigDecimal co2Saved, @RequestParam(required=false) Integer garmentRating, @RequestParam(required = false) String details, @RequestParam(required=false) String createdBy) {

        return garmentService.createGarment(id, category, seller, garmentImage, garmentStatus, description, material, defects, price, co2Saved, garmentRating, details, createdBy);
    }


    @PostMapping("garment/{id}/edit")
    public @ResponseBody String editGarment (@PathVariable String id, @RequestParam  String description, @RequestParam String material, @RequestParam String defects, @RequestParam Integer price, @RequestParam BigDecimal co2Saved, @RequestParam Integer garmentRating, @RequestParam String details, @RequestParam String updatedBy) {
        return garmentService.editGarment(id, description, material, defects, price, co2Saved, garmentRating, details, updatedBy);
    }

    @PostMapping("garment/{id}/delete")
    public @ResponseBody String deleteGarment (@PathVariable String id, @RequestParam String deletedBy) {
        return garmentService.deleteGarment(id, deletedBy);
    }
}
