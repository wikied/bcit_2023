package com.springserver.api.controller;

import com.springserver.api.model.SubCategory;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.SubCategoryRepository;
import com.springserver.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/sub-category")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @PostMapping
    public @ResponseBody String createSubCategory (Authentication authentication, @RequestBody String name) {
        String createdBy = authentication.getName();
        return subCategoryService.createSubCategory(name, createdBy);
    }

    @GetMapping("/{id}")
    public @ResponseBody SubCategory findSubCategoryById (@PathVariable String id)  {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (subCategory.isEmpty()) {
            throw new ResourceNotFoundException("Sub Category", "id", id);
        }

        return subCategoryService.findSubCategoryById(id);
    }

    @GetMapping
    public @ResponseBody Iterable<SubCategory> getAllSubCategories() {
        return subCategoryService.getAllSubCategories();
    }

    @PutMapping("/{id}")
    public @ResponseBody String editSubCategory (Authentication authentication, @PathVariable String id, @RequestBody String name) {
        String updatedBy = authentication.getName();
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (subCategory.isEmpty()) {
            throw new ResourceNotFoundException("Sub Category", "id", id);
        }

        return subCategoryService.editSubCategory(id, name, updatedBy);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteSubCategory (Authentication authentication, @PathVariable String id) {
        String deletedBy = authentication.getName();
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);
        if (subCategory.isEmpty()) {
            throw new ResourceNotFoundException("Sub Category", "id", id);
        }

        return subCategoryService.deleteSubCategory(id, deletedBy);
    }

}
