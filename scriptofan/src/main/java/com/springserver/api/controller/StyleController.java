package com.springserver.api.controller;

import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.StyleRepository;
import com.springserver.api.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Style;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<Style> getStyle (@PathVariable String id) {
        Optional<Style> getStyle = styleRepository.findById(id);
        if (getStyle.isPresent()) {
            Style style = getStyle.get();
            return ResponseEntity.ok(style);
        }
        throw new ResourceNotFoundException("Style", "id", id);
    }

    //create style
    @PostMapping
    public ResponseEntity<Style> createStyle(Authentication authentication, @RequestBody Style style) {
        String createdBy = authentication.getName();
        Style newstyle = styleService.createStyle(style, createdBy);
        if (newstyle == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newstyle);
    }

    //update style
    @PutMapping("/{id}")
    public ResponseEntity<Style> updateStyle (Authentication authentication, @RequestBody Style style, @PathVariable String id) {
        String updatedBy = authentication.getName();
        Style updateStyle = styleService.updateStyle(id, style, updatedBy);
        if (updateStyle == null) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
        return ResponseEntity.ok(updateStyle);
    }
    //delete style
    @DeleteMapping("/{id}")
    public ResponseEntity<Style> deleteStyle (Authentication authentication, @PathVariable String id) {
        String deletedBy = authentication.getName();
        Style deleteStyle = styleService.deleteStyle(id, deletedBy);
        if (deleteStyle == null) {
            throw new ResourceNotFoundException("Style", "id", id);
        }
        return ResponseEntity.ok(deleteStyle);
    }
}
