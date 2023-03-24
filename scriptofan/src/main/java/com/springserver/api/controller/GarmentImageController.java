package com.springserver.api.controller;


import com.springserver.api.model.GarmentImage;
import com.springserver.api.model.Seller;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.GarmentImageRepository;
import com.springserver.api.service.GarmentImageService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/garment-img")
public class GarmentImageController {

    @Autowired
    private GarmentImageRepository garmentImageRepository;

    @Autowired
    private GarmentImageService garmentImageService;


    @GetMapping
    public @ResponseBody Iterable<GarmentImage> getAllGarmentImages() {
        return garmentImageRepository.findAll();
    }

    @PostMapping
    @PermitAll
    public @ResponseBody GarmentImage addNewImage(Authentication authentication, @RequestParam String imageUrl){
        return garmentImageService.createGarmentImage(imageUrl, authentication.getName());
    }

    @PutMapping("/{id}")
    public GarmentImage updateImage(Authentication authentication, @PathVariable String id, @RequestBody GarmentImage garmentImageRequest){
        Optional<GarmentImage> garmentImage = garmentImageRepository.findById(id);
        if (garmentImage.isEmpty()) {
            throw new ResourceNotFoundException("GarmentImage", "id", id);
        }

        return garmentImageService.update(garmentImage.get(), garmentImageRequest, authentication.getName());
    }

    @GetMapping("/{id}")
    public @ResponseBody GarmentImage findGarmentImageByID(@PathVariable String id){
        Optional<GarmentImage> garmentImage = garmentImageRepository.findById(id);
        if(garmentImage.isEmpty()){
            throw new ResourceNotFoundException("GarmentImage", "id", id);
        }
        return garmentImageRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGarmentImage(Authentication authentication, @PathVariable String id){
        Optional<GarmentImage> garmentImage = garmentImageRepository.findById(id);
        if(garmentImage.isEmpty()){
            throw new ResourceNotFoundException("GarmentImage", "id", id);
        }
        garmentImageService.delete(garmentImage.get(), authentication.getName());
        return ResponseEntity.ok().build();
    }
}
