package com.springserver.api.service;

import com.springserver.api.model.Gender;
import com.springserver.api.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public String createGender (String name, String createdBy) {
        Gender gender = new Gender();
        gender.setName(name);
        gender.setCreateTime(Instant.now());
        gender.setCreatedBy(createdBy);
        genderRepository.save(gender);
        return "Gender created";
    }

    public Iterable<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    public Gender findGenderById (String id)  {
        return genderRepository.findById(id).get();
    }

    public String editGender (String id, String name, String updatedBy) {
        Gender genderID = genderRepository.findById(id).get();
        genderID.setName(name);
        genderID.setUpdateTime(Instant.now());
        genderID.setUpdatedBy(updatedBy);
        genderRepository.save(genderID);
        return "Gender updated";
    }

    public String deleteGender (String id, String deletedBy) {
        Gender genderID = genderRepository.findById(id).get();
        genderID.setDeleteTime(Instant.now());
        genderID.setDeletedBy(deletedBy);
        genderRepository.save(genderID);
        return "Gender deleted";
    }
}
