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
    public Size createSize(Size size, String createdBy) {
        if (size == null || size.getName() == null || size.getName().trim().isEmpty()) {
            return null;
        }
        size.setCreatedBy(createdBy);
        size.setCreateTime(Instant.now());
        Size createSize = sizeRepository.save(size);
        return createSize;
    }

    //update size
    public Size updateSize(String id, Size updatedSize, String updatedBy) {
        Size existingSize = sizeRepository.findById(id).orElse(null);

        if (existingSize == null) {
            return null;
        }
        if (updatedSize == null || updatedSize.getName() == null || updatedSize.getName().trim().isEmpty()) {
            return null;
        }
        existingSize.setName(updatedSize.getName());
        existingSize.setUpdateTime(Instant.now());
        existingSize.setUpdatedBy(updatedBy);

        Size updatedSizeSave = sizeRepository.save(existingSize);

        return updatedSizeSave;
    }

    //delete size
    public Size deleteSize(String id, String deletedBy) {

        Size sizeToDelete = sizeRepository.findById(id).orElse(null);

        if (sizeToDelete == null) {
            return null;
        }
        sizeToDelete.setDeleteTime(Instant.now());
        sizeToDelete.setDeletedBy(deletedBy);

        Size deleteSizeSave = sizeRepository.save(sizeToDelete);

        return deleteSizeSave;
    }

}
