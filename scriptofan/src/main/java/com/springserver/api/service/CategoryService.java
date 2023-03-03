package com.springserver.api.service;
import com.springserver.api.model.Category;
import com.springserver.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String addNewCategory(String categoryName, String description, String createBy) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        if (createBy == null || createBy.isEmpty()) {
            throw new IllegalArgumentException("Created by field cannot be empty");
        }

        Category newCategory = new Category();
        newCategory.setCategoryName(categoryName);
        newCategory.setCreatedBy(createBy);
        newCategory.setDescription(description);
        newCategory.setUpdateTime(Instant.now());
        categoryRepository.save(newCategory);

        return "Saved";
    }

    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(String id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
        return optionalCategory.get();
    }

    public String editCategory(String id, String categoryName, String description, String updateBy) {
        Category category = findCategoryById(id);
        category.setCategoryName(categoryName);
        category.setDescription(description);
        category.setUpdatedBy(updateBy);
        category.setUpdateTime(Instant.now());
        categoryRepository.save(category);
        return "Saved";
    }

    public String removeCategory(String id, String deletedBy) {
        Category category = findCategoryById(id);
        category.setDeletedBy(deletedBy);
        category.setDeleteTime(Instant.now());
        categoryRepository.save(category);
        return "Saved";
    }

}