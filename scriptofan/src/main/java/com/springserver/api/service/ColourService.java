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
    public Colour createColour(String id, String name, String createdBy) {
        Colour createColour = new Colour();
        createColour.setId(id);
        createColour.setName(name);
        createColour.setCreateTime(Instant.now());
        createColour.setCreatedBy(createdBy);
        return colourRepository.save(createColour);
    }

    //update colour
    public Colour updateColour(String id, String name, String updatedBy) {
        Colour updatebyid = colourRepository.findById(id).get();
        updatebyid.setName(name);
        updatebyid.setUpdateTime(Instant.now());
        updatebyid.setUpdatedBy(updatedBy);
        return colourRepository.save(updatebyid);
    }

    //delete colour
    public Colour deleteColour(String id, String deletedBy) {
        Colour deletebyid = colourRepository.findById(id).get();
        deletebyid.setDeleteTime(Instant.now());
        deletebyid.setDeletedBy(deletedBy);
        return colourRepository.save(deletebyid);
    }
}
