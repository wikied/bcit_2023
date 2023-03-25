package com.springserver.api.controller;


import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.SizeRepository;
import com.springserver.api.service.SizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Size;
import java.util.*;


@RestController
@RequestMapping(value = "/size")
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private SizeService sizeService;


    //get all sizes
    @GetMapping
    public @ResponseBody Iterable<Size> getAllSizes() {
        return sizeService.getAllSizes();
    }

    //get size by id
    @GetMapping("/{id}")
    public ResponseEntity<Size> getSize (@PathVariable String id) {
        Optional<Size> getSize = sizeRepository.findById(id);
        if (getSize.isPresent()) {
            Size size = getSize.get();
            return ResponseEntity.ok(size);
        }
        throw new ResourceNotFoundException("Size", "id", id);
    }


    //create size
    @PostMapping
    public ResponseEntity<Size> createSize(Authentication authentication, @RequestBody Size size) {
        String createdBy = authentication.getName();
        Size newsize = sizeService.createSize(size, createdBy);
        if (newsize == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newsize);
    }

    //update size
    @PutMapping("/{id}")
    public ResponseEntity<Size> updateSize (Authentication authentication, @RequestBody Size size, @PathVariable String id) {
        String updatedBy = authentication.getName();
        Size updateSize = sizeService.updateSize(id, size, updatedBy);
        if (updateSize == null) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
        return ResponseEntity.ok(updateSize);
    }
    //delete size
    @DeleteMapping("/{id}")
    public ResponseEntity<Size> deleteSize (Authentication authentication, @PathVariable String id) {
        String deletedBy = authentication.getName();
        Size deleteSize = sizeService.deleteSize(id, deletedBy);
        if (deleteSize == null) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
        return ResponseEntity.ok(deleteSize);
    }
}
