package com.ecom.project.service;

import com.ecom.project.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category>getAllCategories();
    void createCategory(Category category);

}
