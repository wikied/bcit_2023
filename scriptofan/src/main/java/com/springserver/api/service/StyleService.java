package com.springserver.api.service;

import com.springserver.api.model.Style;
import com.springserver.api.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
@Service
public class StyleService {
    @Autowired
    private StyleRepository styleRepository;

    //get all styles
    public Iterable<Style> getAllStyles() { return styleRepository.findAll();}

    //get style by id
    public Style getStyle(String id) {
        return styleRepository.findById(id).get();
    }

    //create style
    public Style createStyle(String id, String name, String createdBy) {
        Style createStyle = new Style();
        createStyle.setId(id);
        createStyle.setName(name);
        createStyle.setCreateTime(Instant.now());
        createStyle.setCreatedBy(createdBy);
        return styleRepository.save(createStyle);
    }

    //update style
    public Style updateStyle(String id, String name, String updatedBy) {
        Style updatebyid = styleRepository.findById(id).get();
        updatebyid.setName(name);
        updatebyid.setUpdateTime(Instant.now());
        updatebyid.setUpdatedBy(updatedBy);
        return styleRepository.save(updatebyid);
    }

    //delete style
    public Style deleteStyle(String id, String deletedBy) {
        Style deletebyid = styleRepository.findById(id).get();
        deletebyid.setDeleteTime(Instant.now());
        deletebyid.setDeletedBy(deletedBy);
        return styleRepository.save(deletebyid);
    }
}
