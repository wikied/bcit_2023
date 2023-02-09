package com.springserver.api.controller;

import com.springserver.api.model.Category;
import com.springserver.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository; //to use category repository

    //to create new category
    @PostMapping("/category/add")
    public @ResponseBody String addNewCategory (@RequestParam  String categoryName, @RequestParam String description) {
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryName);
        newCategory.setDescription(description);
        return "Saved";
    }

    //to find all categories
    @GetMapping("/category/all")
    public @ResponseBody Iterable<Category> createNewCategories(){
        return categoryRepository.findAll();
    }

    //to find category
    @GetMapping("/category/find/{id}")
    public @ResponseBody Category findCategoryById (@PathVariable String id) {
        return categoryRepository.findById(id).get();
    }

    //to edit category
    @PostMapping("category/find/{id}/edit")
    public @ResponseBody String editCategory (@PathVariable String id, @RequestParam  String categoryName, @RequestParam String description) {
        Category categoryID = categoryRepository.findById(id).get();
        categoryID.setCategoryName(categoryName);
        categoryID.setDescription(description);
        return "Saved";
    }


}