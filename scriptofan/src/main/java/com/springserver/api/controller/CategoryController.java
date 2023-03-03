package com.springserver.api.controller;

import com.springserver.api.model.Category;
import com.springserver.api.repository.CategoryRepository;
import com.springserver.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    //to create new category
    @PostMapping("/category/add")
    public @ResponseBody String addNewCategory (@RequestParam String categoryName, @RequestParam String description, @RequestParam String createBy) {
        return categoryService.addNewCategory(categoryName, description, createBy);
    }

    //to find all categories
    @GetMapping("/category/all")
    public @ResponseBody Iterable<Category> createNewCategories(){
        return categoryService.findAllCategories();
    }

    //to find category
    @GetMapping("/category/find/{id}")
    public @ResponseBody Category findCategoryById (@PathVariable String id) {
        return categoryService.findCategoryById(id);
    }

    //to edit category
    @PostMapping("category/find/{id}/edit")
    public @ResponseBody String editCategory (@PathVariable String id, @RequestParam  String categoryName, @RequestParam String description, @RequestParam String updateBy) {
        return categoryService.editCategory(id, categoryName, description, updateBy);
    }

    @PostMapping ("/category/find/{id}/remove")
    public @ResponseBody String removeCategory(@PathVariable String id, @RequestParam String deletedBy) {
        return categoryService.removeCategory(id, deletedBy);
    }


}
