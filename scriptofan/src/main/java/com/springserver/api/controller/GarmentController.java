package com.springserver.api.controller;

import com.springserver.api.model.*;
import com.springserver.api.repository.GarmentRepository;
import com.springserver.api.service.CategoryService;
import com.springserver.api.service.GarmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Garment;
import com.springserver.api.repository.GarmentRepository;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
@RequestMapping("/garment")
public class GarmentController {
    @Autowired
    private GarmentRepository garmentRepository;

    @Autowired
    private GarmentService garmentService;

    @Autowired
    private CategoryService categoryService;

    //get all garments REST API
    @GetMapping
    public @ResponseBody Iterable<Garment> getAllGarments() {
        return garmentService.getAllGarments();
    }

    //get garment by id REST API
    @GetMapping("/{id}")
    public @ResponseBody Garment getGarment (@PathVariable String id) {
        return garmentService.getGarment(id);
    }

    //build create garment REST API
    @PostMapping("/garment/create")
    public @ResponseBody Garment createGarment(Authentication authentication, @RequestParam String category, @RequestParam(required = false) Seller seller, @RequestParam(required = false) GarmentImage garmentImage, @RequestParam(required = false) GarmentStatus garmentStatus,
                                              @RequestParam(required=false) String description, @RequestParam(required = false) String material, @RequestParam(required = false) String defects, @RequestParam(required = false) Integer price,
                                              @RequestParam(required=false) BigDecimal co2Saved, @RequestParam(required=false) Integer garmentRating, @RequestParam(required = false) String details) {
        Category categoryId = categoryService.findCategoryById(category);
        return garmentService.createGarment(categoryId, seller, garmentImage, garmentStatus, description, material, defects, price, co2Saved, garmentRating, details, authentication.getName());
    }


    @PutMapping("/{id}")
    public @ResponseBody String editGarment (Authentication authentication, @PathVariable String id, @RequestParam  String description, @RequestParam String material, @RequestParam String defects, @RequestParam Integer price, @RequestParam BigDecimal co2Saved, @RequestParam Integer garmentRating, @RequestParam String details) {
        return garmentService.editGarment(id, description, material, defects, price, co2Saved, garmentRating, details, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteGarment (Authentication authentication, @PathVariable String id) {
        return garmentService.deleteGarment(id, authentication.getName());
    }
}
