package com.springserver.api.controller;

import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.StyleRepository;
import com.springserver.api.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Style;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/style")
public class StyleController {
    @Autowired
    private StyleRepository styleRepository;
    @Autowired
    private StyleService styleService;

    //get all styles
    @GetMapping("/all")
    public @ResponseBody Iterable<Style> getAllStyles() {
        return styleService.getAllStyles();
    }

    //get style by id
    @GetMapping("/get/{id}")
    public @ResponseBody Style getStyle (@PathVariable String id) {
        try {
            return styleService.getStyle(id);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
    }

    //create style
    @PostMapping("/create")
    public @ResponseBody Style createStyle (@RequestParam String id, @RequestParam String name, @RequestParam String createdBy) {
        return styleService.createStyle(id, name, createdBy);
    }

    //update style
    @PostMapping("/update/{id}")
    public @ResponseBody Style updateStyle (@PathVariable String id, @RequestParam String name, @RequestParam String updatedBy) {
        try {
            return styleService.updateStyle(id, name, updatedBy);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
    }
    //delete style
    @PostMapping("/delete/{id}")
    public @ResponseBody Style deleteStyle (@PathVariable String id, @RequestParam String deletedBy) {
        try {
            return styleService.deleteStyle(id, deletedBy);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
    }
}
