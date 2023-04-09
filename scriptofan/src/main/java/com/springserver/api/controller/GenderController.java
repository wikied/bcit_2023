package com.springserver.api.controller;

import com.springserver.api.model.Gender;
import com.springserver.api.repository.GenderRepository;
import com.springserver.api.service.GenderService;
import com.springserver.api.provider.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @Autowired
    private GenderRepository genderRepository;


    @PostMapping
    public @ResponseBody String createGender (Authentication authentication, @RequestBody String name) {
        String createdBy = authentication.getName();
        return genderService.createGender(name, createdBy);
    }

    @GetMapping("/{id}")
    public @ResponseBody Gender findGenderById (@PathVariable String id) {
        Optional<Gender> gender = genderRepository.findById(id);
        if (gender.isEmpty()) {
            throw new ResourceNotFoundException("Gender", "id", id);
        }

        return genderService.findGenderById(id);
    }

    @GetMapping
    public @ResponseBody Iterable<Gender> getAllGenders() {
        return genderService.getAllGenders();
    }

    @PutMapping("/{id}")
    public @ResponseBody String editGender (Authentication authentication, @PathVariable String id, @RequestBody String name) {
        String updatedBy = authentication.getName();
        Optional<Gender> gender = genderRepository.findById(id);
        if (gender.isEmpty()) {
            throw new ResourceNotFoundException("Gender", "id", id);
        }

        return genderService.editGender(id, name, updatedBy);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteGender (Authentication authentication, @PathVariable String id) {
        String deletedBy = authentication.getName();
        Optional<Gender> gender = genderRepository.findById(id);
        if (gender.isEmpty()) {
            throw new ResourceNotFoundException("Gender", "id", id);
        }

        return genderService.deleteGender(id, deletedBy);
    }

}
