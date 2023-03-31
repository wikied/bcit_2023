package com.springserver.api.controller;


import com.springserver.api.model.GarmentImage;
import com.springserver.api.model.GarmentStatus;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.GarmentStatusRepository;
import com.springserver.api.service.GarmentStatusService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/garment-status")
public class GarmentStatusController {

    @Autowired
    private GarmentStatusRepository garmentStatusRepository;

    @Autowired
    private GarmentStatusService garmentStatusService;

    @GetMapping
    public @ResponseBody Iterable<GarmentStatus> getAllGarmentStatuses(){
        return garmentStatusRepository.findAll();
    }

    @PostMapping
    @PermitAll
    public GarmentStatus addNewStatus(Authentication authentication,
                                      @RequestBody Short posted,
                                      @RequestBody Boolean inStore,
                                      @RequestBody Boolean pendingPurchase,
                                      @RequestBody Boolean purchased){
        return garmentStatusService.createGarmentStatus(posted, inStore, pendingPurchase, purchased, authentication.getName());
    }

    @PutMapping("/{id}")
    public GarmentStatus updateStatus(Authentication authentication, @PathVariable String id, @RequestBody GarmentStatus garmentStatusRequest){
        Optional<GarmentStatus> garmentStatus = garmentStatusRepository.findById(id);
        if (garmentStatus.isEmpty()) {
            throw new ResourceNotFoundException("GarmentStatus", "id", id);
        }

        return garmentStatusService.update(garmentStatus.get(), garmentStatusRequest, authentication.getName());
    }

    @GetMapping("/{id}")
    public @ResponseBody GarmentStatus findGarmentStatusByID(@PathVariable String id){
        Optional<GarmentStatus> garmentStatus = garmentStatusRepository.findById(id);
        if(garmentStatus.isEmpty()){
            throw new ResourceNotFoundException("GarmentStatus", "id", id);
        }
        return garmentStatusRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGarmentStatus(Authentication authentication, @PathVariable String id){
        Optional<GarmentStatus> garmentStatus = garmentStatusRepository.findById(id);
        if(garmentStatus.isEmpty()){
            throw new ResourceNotFoundException("GarmentStatus", "id", id);
        }
        garmentStatusService.delete(garmentStatus.get(), authentication.getName());
        return ResponseEntity.ok().build();
    }
}
