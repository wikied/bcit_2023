package com.springserver.api.controller;

import com.springserver.api.model.*;
import com.springserver.api.repository.StyleRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Style;

import java.time.Instant;

@RestController
public class StyleController {
    @Autowired
    private StyleRepository styleRepository;

    //get all styles
    @GetMapping("/style/all")
    public @ResponseBody Iterable<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    //get style by id
    @GetMapping("/style/get/{id}")
    public @ResponseBody Style getStyle (@PathVariable String id) {
        return styleRepository.findById(id).get();
    }

    //create style
    @PostMapping("/style/create")
    public @ResponseBody String createStyle (@RequestParam String id, @RequestParam String name, @RequestParam(required=false) String createdBy) {
        Style createStyle = new Style();
        createStyle.setId(id);
        createStyle.setName(name);
        createStyle.setCreateTime(Instant.now());
        createStyle.setCreatedBy(createdBy);
        styleRepository.save(createStyle);
        return "Style has been created!";
    }

    //update style
    @PostMapping("style/update/{id}")
    public @ResponseBody String updateStyle (@PathVariable String id, @RequestParam String name, @RequestParam String updatedBy) {
        Style updatebyid = styleRepository.findById(id).get();
        updatebyid.setName(name);
        updatebyid.setUpdateTime(Instant.now());
        updatebyid.setUpdatedBy(updatedBy);
        styleRepository.save(updatebyid);
        return "Style updated!";
    }
    //delete style
    @PostMapping("style/delete/{id}")
    public @ResponseBody String deleteStyle (@PathVariable String id, @RequestParam String deletedBy) {
        Style deletebyid = styleRepository.findById(id).get();
        deletebyid.setDeleteTime(Instant.now());
        deletebyid.setDeletedBy(deletedBy);
        styleRepository.save(deletebyid);
        return "Style deleted!";
    }
}
