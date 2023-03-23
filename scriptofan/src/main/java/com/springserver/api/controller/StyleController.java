package com.springserver.api.controller;

import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.StyleRepository;
import com.springserver.api.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @GetMapping
    public @ResponseBody Iterable<Style> getAllStyles() {
        return styleService.getAllStyles();
    }

    //get style by id
    @GetMapping("/{id}")
    public @ResponseBody Style getStyle (@PathVariable String id) {
        try {
            return styleService.getStyle(id);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
    }

    //create style
    @PostMapping
    public @ResponseBody Style createStyle (Authentication authentication, @RequestParam String id, @RequestParam String name) {
        return styleService.createStyle(id, name, authentication.getName());
    }

    //update style
    @PutMapping("/{id}")
    public @ResponseBody Style updateStyle (Authentication authentication, @PathVariable String id, @RequestParam String name) {
        try {
            return styleService.updateStyle(id, name, authentication.getName());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
    }
    //delete style
    @DeleteMapping("/{id}")
    public @ResponseBody Style deleteStyle (Authentication authentication, @PathVariable String id) {
        try {
            return styleService.deleteStyle(id, authentication.getName());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
    }
}
