package com.ecom.project.controller;

import com.ecom.project.model.Category;
import com.ecom.project.service.CategoryService;
import com.ecom.project.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired // no need to write constructor
    private  CategoryService categoryService;

    // Instead of this constructor we should do autowired

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
    @GetMapping("/api/public/categories")
     public List<Category>getAllCategories(){
         return categoryService.getAllCategories();
     }

     @PostMapping("/api/public/categories")
     public String createCategory(@RequestBody Category category){
         categoryService.createCategory(category);
         return "Category added successfully";
     }
     @DeleteMapping("/api/admin/categories/{categoryId}")
     public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
            String status=categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status,HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
     }

}
