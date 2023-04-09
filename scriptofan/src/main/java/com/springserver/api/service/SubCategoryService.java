package com.springserver.api.service;

import com.springserver.api.model.SubCategory;
import com.springserver.api.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public String createSubCategory (String name, String createdBy) {
        SubCategory subCat = new SubCategory();
        subCat.setName(name);
        subCat.setCreateTime(Instant.now());
        subCat.setCreatedBy(createdBy);
        subCategoryRepository.save(subCat);
        return "Sub Category created";
    }

    public Iterable<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    public SubCategory findSubCategoryById (String id)  {
        return subCategoryRepository.findById(id).get();
    }

    public String editSubCategory (String id, String name, String updatedBy) {
        SubCategory subCatID = subCategoryRepository.findById(id).get();
        subCatID.setName(name);
        subCatID.setUpdateTime(Instant.now());
        subCatID.setUpdatedBy(updatedBy);
        subCategoryRepository.save(subCatID);
        return "Sub Category updated";
    }

    public String deleteSubCategory (String id, String deletedBy) {
        SubCategory subCatID = subCategoryRepository.findById(id).get();
        subCatID.setDeleteTime(Instant.now());
        subCatID.setDeletedBy(deletedBy);
        subCategoryRepository.save(subCatID);
        return "Sub Category deleted";
    }
}
