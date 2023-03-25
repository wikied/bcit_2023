package com.springserver.api.controller;

import com.springserver.api.model.Category;
import com.springserver.api.repository.CategoryRepository;
import com.springserver.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    //to create new category
    @PostMapping
    public @ResponseBody String addNewCategory (Authentication authentication, @RequestParam String categoryName, @RequestParam String description) {
        return categoryService.addNewCategory(categoryName, description, authentication.getName());
    }

    //to find all categories
    @GetMapping
    public @ResponseBody Iterable<Category> createNewCategories(){
        return categoryService.findAllCategories();
    }

    //to find category
    @GetMapping("/{id}")
    public @ResponseBody Category findCategoryById (@PathVariable String id) {
        return categoryService.findCategoryById(id);
    }

    //to edit category
    @PutMapping("/{id}")
    public @ResponseBody String editCategory (Authentication authentication, @PathVariable String id, @RequestParam  String categoryName, @RequestParam String description) {
        return categoryService.editCategory(id, categoryName, description, authentication.getName());
    }

    @PostMapping ("/{id}")
    public @ResponseBody String removeCategory(Authentication authentication, @PathVariable String id) {
        return categoryService.removeCategory(id, authentication.getName());
    }


}
