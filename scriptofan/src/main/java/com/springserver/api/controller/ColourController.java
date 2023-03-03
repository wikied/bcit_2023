package com.springserver.api.controller;

import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.ColourRepository;
import com.springserver.api.service.ColourService;
import com.springserver.api.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Colour;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/colour")
public class ColourController {
    @Autowired
    private ColourRepository colourRepository;
    @Autowired
    private ColourService colourService;

    //get all colours
    @GetMapping("/all")
    public @ResponseBody Iterable<Colour> getAllColours() {
        return colourService.getAllColours();
    }

    //get colour by id
    @GetMapping("/get/{id}")
    public @ResponseBody Colour getColour (@PathVariable String id) {
        try {
            return colourService.getColour(id);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
    }

    //create colour
    @PostMapping("/create")
    public @ResponseBody Colour createColour(@RequestParam String id, @RequestParam String name, @RequestParam String createdBy) {
        return colourService.createColour(id, name, createdBy);
    }

    //update colour
    @PostMapping("/update/{id}")
    public @ResponseBody Colour updateColour (@PathVariable String id, @RequestParam String name, @RequestParam String updatedBy) {
        try {
            return colourService.updateColour(id, name, updatedBy);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
    }
    //delete colour
    @PostMapping("/delete/{id}")
    public @ResponseBody Colour deleteColour (@PathVariable String id, @RequestParam String deletedBy) {
        try {
            return colourService.deleteColour(id, deletedBy);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Colour", "id", id);
        }
    }
}
