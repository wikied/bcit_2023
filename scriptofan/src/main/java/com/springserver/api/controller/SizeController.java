package com.springserver.api.controller;

import com.springserver.api.model.*;
import com.springserver.api.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springserver.api.model.Size;

import java.time.Instant;

@RestController
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;

    //get all sizes
    @GetMapping("/size/all")
    public @ResponseBody Iterable<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    //get size by id
    @GetMapping("/size/get/{id}")
    public @ResponseBody Size getSize (@PathVariable String id) {
        return sizeRepository.findById(id).get();
    }

    //create size
    @PostMapping("/size/create")
    public @ResponseBody String createSize(@RequestParam String id, @RequestParam String name, @RequestParam String createdBy) {
        Size createSize = new Size();
        createSize.setId(id);
        createSize.setName(name);
        createSize.setCreateTime(Instant.now());
        createSize.setCreatedBy(createdBy);
        sizeRepository.save(createSize);
        return "Size has been created!";
    }

    //update size
    @PostMapping("size/update/{id}")
    public @ResponseBody String updateColour (@PathVariable String id, @RequestParam String name, @RequestParam String updatedBy) {
        Size updatebyid = sizeRepository.findById(id).get();
        updatebyid.setName(name);
        updatebyid.setUpdateTime(Instant.now());
        updatebyid.setUpdatedBy(updatedBy);
        sizeRepository.save(updatebyid);
        return "Size updated!";
    }
    //delete size
    @PostMapping("Colour/delete/{id}")
    public @ResponseBody String updateColour (@PathVariable String id, @RequestParam String deletedBy) {
        Size deletebyid = sizeRepository.findById(id).get();
        deletebyid.setDeleteTime(Instant.now());
        deletebyid.setDeletedBy(deletedBy);
        sizeRepository.save(deletebyid);
        return "Size deleted!";
    }
}
