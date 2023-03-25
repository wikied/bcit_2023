package com.springserver.api.service;

import com.springserver.api.model.Colour;
import com.springserver.api.repository.ColourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
@Service
public class ColourService {
    @Autowired
    private ColourRepository colourRepository;

    //get all colours
    public Iterable<Colour> getAllColours() { return colourRepository.findAll();}

    //get colour by id
    public Colour getColour(String id) {
        return colourRepository.findById(id).get();
    }

    //create colour
    public Colour createColour(Colour colour, String createdBy) {
        if (colour == null || colour.getName() == null || colour.getName().trim().isEmpty()) {
            return null;
        }
        colour.setCreatedBy(createdBy);
        colour.setCreateTime(Instant.now());
        Colour createcolour = colourRepository.save(colour);
        return createcolour;
    }
    //update colour
    public Colour updateColour(String id, Colour updatedColour, String updatedBy) {
        Colour existingColour = colourRepository.findById(id).orElse(null);

        if (existingColour == null) {
            return null;
        }
        if (updatedColour == null || updatedColour.getName() == null || updatedColour.getName().trim().isEmpty()) {
            return null;
        }
        existingColour.setName(updatedColour.getName());
        existingColour.setUpdateTime(Instant.now());
        existingColour.setUpdatedBy(updatedBy);

        Colour updatedColourSave = colourRepository.save(existingColour);

        return updatedColourSave;
    }

    //delete colour
    public Colour deleteColour(String id, String deletedBy) {

        Colour colourToDelete = colourRepository.findById(id).orElse(null);

        if (colourToDelete == null) {
            return null;
        }
        colourToDelete.setDeleteTime(Instant.now());
        colourToDelete.setDeletedBy(deletedBy);

        Colour deletecolourSave = colourRepository.save(colourToDelete);

        return deletecolourSave;
    }
}
