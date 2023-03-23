package com.springserver.api.controller;


import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.SizeRepository;
import com.springserver.api.service.SizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Size;
import java.util.*;


@RestController
@RequestMapping(value = "/size")
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private SizeService sizeService;



    //get all sizes
    @GetMapping
    public @ResponseBody Iterable<Size> getAllSizes() {
        return sizeService.getAllSizes();
    }

    //get size by id
    @GetMapping("/{id}")
    public @ResponseBody Size getSize (@PathVariable String id) {
        try {
             return sizeService.getSize(id);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
    }

    //create size
    @PostMapping
    public @ResponseBody Size createSize(Authentication authentication, @RequestParam String id, @RequestParam String name) {
        return sizeService.createSize(id, name, authentication.getName());
    }

    //update size
    @PutMapping("/{id}")
    public @ResponseBody Size updateSize (Authentication authentication, @PathVariable String id, @RequestParam String name) {
        try {
            return sizeService.updateSize(id, name, authentication.getName());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
    }
    //delete size
    @DeleteMapping("/{id}")
    public @ResponseBody Size updateSize (Authentication authentication, @PathVariable String id) {
        try {
            return sizeService.deleteSize(id, authentication.getName());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
    }
}
