package com.springserver.api.controller;

import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.service.ColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Colour;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/colour")
public class ColourController {

    @Autowired
    private ColourService colourService;

    //get all colours
    @GetMapping
    public @ResponseBody Iterable<Colour> getAllColours() {
        return colourService.getAllColours();
    }

    //get colour by id
    @GetMapping("/{id}")
    public @ResponseBody Colour getColour (@PathVariable String id) {
        try {
            return colourService.getColour(id);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
    }

    //create colour
    @PostMapping
    public @ResponseBody Colour createColour(Authentication authentication, @RequestParam String id, @RequestParam String name) {
        return colourService.createColour(id, name, authentication.getName());
    }

    //update colour
    @PutMapping("/{id}")
    public @ResponseBody Colour updateColour (Authentication authentication, @PathVariable String id, @RequestParam String name) {
        try {
            return colourService.updateColour(id, name, authentication.getName());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
    }
    //delete colour
    @DeleteMapping("/{id}")
    public @ResponseBody Colour deleteColour (Authentication authentication, @PathVariable String id) {
        try {
            return colourService.deleteColour(id, authentication.getName());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
    }
}
