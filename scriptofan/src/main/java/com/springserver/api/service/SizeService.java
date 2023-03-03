package com.springserver.api.service;

import com.springserver.api.model.Size;
import com.springserver.api.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    //get all sizes
    public Iterable<Size> getAllSizes() { return sizeRepository.findAll();}

    //get size by id
    public Size getSize(String id) {
        return sizeRepository.findById(id).get();
    }

    //create size
    public Size createSize(String id, String name, String createdBy) {
        Size size = new Size();
        size.setId(id);
        size.setName(name);
        size.setCreateTime(Instant.now());
        size.setCreatedBy(createdBy);
        return sizeRepository.save(size);
    }

    //update size
    public Size updateSize(String id, String name, String updatedBy) {
        Size updatebyid = sizeRepository.findById(id).get();
        updatebyid.setName(name);
        updatebyid.setUpdateTime(Instant.now());
        updatebyid.setUpdatedBy(updatedBy);
        return sizeRepository.save(updatebyid);
    }

    //delete size
    public Size deleteSize(String id, String deletedBy) {
        Size deletebyid = sizeRepository.findById(id).get();
        deletebyid.setDeleteTime(Instant.now());
        deletebyid.setDeletedBy(deletedBy);
        return sizeRepository.save(deletebyid);
    }

}
