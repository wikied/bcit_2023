package com.springserver.api.controller;

import com.springserver.api.model.*;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.GarmentRepository;
import com.springserver.api.service.CategoryService;
import com.springserver.api.service.GarmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Garment;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/garment")
public class GarmentController {

    @Autowired
    private GarmentService garmentService;
    @Autowired
    private GarmentRepository garmentRepository;

    @Autowired
    private CategoryService categoryService;

    //get all garments REST API
    @GetMapping
    public @ResponseBody Iterable<Garment> getAllGarments() {
        return garmentService.getAllGarments();
    }

    //get garment by id REST API
    @GetMapping("/{id}")
    public ResponseEntity<Garment>  getGarment (@PathVariable String id) {
        Optional<Garment> getGarment = garmentRepository.findById(id);
        if (getGarment.isPresent()) {
            Garment garment = getGarment.get();
            return ResponseEntity.ok(garment);
        }
        throw new ResourceNotFoundException("garment", "id", id);
    }

    //build create garment REST API
    @PostMapping
    public ResponseEntity<Garment> createGarment(Authentication authentication, @RequestBody Garment garment) {
        Garment newGarment = garmentService.createGarment(garment, authentication.getName());
        if (newGarment == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newGarment);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Garment> editGarment (Authentication authentication, @RequestBody Garment garment, @PathVariable String id) {
        String updatedBy = authentication.getName();
        Garment updategarment = garmentService.editGarment(id, garment, updatedBy);
        if (updategarment == null) {
            throw new ResourceNotFoundException("garment", "id", id);
        }
        return ResponseEntity.ok(updategarment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Garment> deleteGarment (Authentication authentication, @PathVariable String id) {
        String deletedBy = authentication.getName();
        Garment deleteGarment = garmentService.deleteGarment(id, deletedBy);
        if (deleteGarment == null) {
            throw new ResourceNotFoundException("garment", "id", id);
        }
        return ResponseEntity.ok(deleteGarment);
    }
}
