package com.ecom.project.service;
import com.ecom.project.model.Category;
import com.ecom.project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    void updateCategory(Category category, Long categoryId);
}
