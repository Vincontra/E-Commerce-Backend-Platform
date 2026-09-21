package com.ecom.project.service;
import com.ecom.project.model.Category;
import com.ecom.project.payload.CategoryDTO;
import com.ecom.project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
//    void createCategory(Category category);
    // Applying DTO for createCategory
    CategoryDTO createCategory(CategoryDTO categoryDto);

    CategoryDTO deleteCategory(Long categoryId);

//    void updateCategory(Category category, Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryId);
}
