package com.springserver.api.controller;

import com.springserver.api.model.*;
import com.springserver.api.repository.ColourRepository;
import com.springserver.api.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Colour;

import java.time.Instant;

@RestController
public class ColourController {
    @Autowired
    private ColourRepository colourRepository;

    //get all colours
    @GetMapping("/colour/all")
    public @ResponseBody Iterable<Colour> getAllColours() {
        return colourRepository.findAll();
    }

    //get colour by id
    @GetMapping("/colour/get/{id}")
    public @ResponseBody Colour getColour (@PathVariable String id) {
        return colourRepository.findById(id).get();
    }

    //create colour
    @PostMapping("/style/create")
    public @ResponseBody String createColour(@RequestParam String id, @RequestParam String name, @RequestParam String createdBy) {
        Colour createColour = new Colour();
        createColour.setId(id);
        createColour.setName(name);
        createColour.setCreateTime(Instant.now());
        createColour.setCreatedBy(createdBy);
        colourRepository.save(createColour);
        return "Colour has been created!";
    }

    //update colour
    @PostMapping("colour/update/{id}")
    public @ResponseBody String updateColour (@PathVariable String id, @RequestParam String name, @RequestParam String updatedBy) {
        Colour updatebyid = colourRepository.findById(id).get();
        updatebyid.setName(name);
        updatebyid.setUpdateTime(Instant.now());
        updatebyid.setUpdatedBy(updatedBy);
        colourRepository.save(updatebyid);
        return "Colour updated!";
    }
    //delete colour
    @PostMapping("Colour/delete/{id}")
    public @ResponseBody String deleteColour (@PathVariable String id, @RequestParam String deletedBy) {
        Colour deletebyid = colourRepository.findById(id).get();
        deletebyid.setDeleteTime(Instant.now());
        deletebyid.setDeletedBy(deletedBy);
        colourRepository.save(deletebyid);
        return "Colour deleted!";
    }
}
