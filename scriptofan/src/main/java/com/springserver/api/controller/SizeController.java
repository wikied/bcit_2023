package com.springserver.api.controller;


import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.SizeRepository;
import com.springserver.api.service.SizeService;

import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/all")
    public @ResponseBody Iterable<Size> getAllSizes() {
        return sizeService.getAllSizes();
    }

    //get size by id
    @GetMapping("/get/{id}")
    public @ResponseBody Size getSize (@PathVariable String id) {
        try {
             return sizeService.getSize(id);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
    }

    //create size
    @PostMapping("/create")
    public @ResponseBody Size createSize(@RequestParam String id, @RequestParam String name, @RequestParam String createdBy) {
        return sizeService.createSize(id, name, createdBy);
    }

    //update size
    @PostMapping("/update/{id}")
    public @ResponseBody Size updateSize (@PathVariable String id, @RequestParam String name, @RequestParam String updatedBy) {
        try {
            return sizeService.updateSize(id, name, updatedBy);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
    }
    //delete size
    @PostMapping("/delete/{id}")
    public @ResponseBody Size updateSize (@PathVariable String id, @RequestParam String deletedBy) {
        try {
            return sizeService.deleteSize(id, deletedBy);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Size", "id", id);
        }
    }
}
