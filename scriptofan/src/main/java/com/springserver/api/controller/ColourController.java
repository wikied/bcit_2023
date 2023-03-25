package com.springserver.api.controller;

import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.ColourRepository;
import com.springserver.api.service.ColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Colour;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import java.util.Optional;


@RestController
@RequestMapping(value = "/colour")
public class ColourController {
    @Autowired
    private ColourRepository colourRepository;
    @Autowired
    private ColourService colourService;

    //get all colours
    @GetMapping
    public @ResponseBody Iterable<Colour> getAllColours() {
        return colourService.getAllColours();
    }

    //get colour by id
    @GetMapping("/{id}")
    public ResponseEntity<Colour> getColour (@PathVariable String id) {
        Optional<Colour> getColour = colourRepository.findById(id);
        if (getColour.isPresent()) {
            Colour colour = getColour.get();
            return ResponseEntity.ok(colour);
        }
        throw new ResourceNotFoundException("Colour", "id", id);
    }

    //create colour
    @PostMapping
    public ResponseEntity<Colour> createColour(Authentication authentication, @RequestBody Colour colour) {
        String createdBy = authentication.getName();
        Colour newColour = colourService.createColour(colour, createdBy);
        if (newColour == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newColour);
    }

    //update colour
    @PutMapping("/{id}")
    public ResponseEntity<Colour> updateColour (Authentication authentication, @RequestBody Colour colour, @PathVariable String id) {
        String updatedBy = authentication.getName();
        Colour updateColour = colourService.updateColour(id, colour, updatedBy);
        if (updateColour == null) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
        return ResponseEntity.ok(updateColour);
    }
    //delete colour
    @DeleteMapping("/{id}")
    public ResponseEntity<Colour> deleteColour (Authentication authentication, @PathVariable String id) {
        String deletedBy = authentication.getName();
        Colour deleteColour = colourService.deleteColour(id, deletedBy);
        if (deleteColour == null) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
        return ResponseEntity.ok(deleteColour);
    }
}
