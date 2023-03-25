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
    public Style createStyle(Style style, String createdBy) {
        if (style == null || style.getName() == null || style.getName().trim().isEmpty()) {
            return null;
        }
        style.setCreatedBy(createdBy);
        style.setCreateTime(Instant.now());
        Style createStyle = styleRepository.save(style);
        return createStyle;
    }

    //update style
    public Style updateStyle(String id, Style updatedStyle, String updatedBy) {
        Style existingStyle = styleRepository.findById(id).orElse(null);

        if (existingStyle == null) {
            return null;
        }
        if (updatedStyle == null || updatedStyle.getName() == null || updatedStyle.getName().trim().isEmpty()) {
            return null;
        }
        existingStyle.setName(updatedStyle.getName());
        existingStyle.setUpdateTime(Instant.now());
        existingStyle.setUpdatedBy(updatedBy);

        Style updatedStyleSave = styleRepository.save(existingStyle);

        return updatedStyleSave;
    }

    //delete style
    public Style deleteStyle(String id, String deletedBy) {

        Style styleToDelete = styleRepository.findById(id).orElse(null);

        if (styleToDelete == null) {
            return null;
        }
        styleToDelete.setDeleteTime(Instant.now());
        styleToDelete.setDeletedBy(deletedBy);

        Style deleteStyleSave = styleRepository.save(styleToDelete);

        return deleteStyleSave;
    }
}
