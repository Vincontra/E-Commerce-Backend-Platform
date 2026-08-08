package com.ecom.project.service;

import com.ecom.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// All the bussiness logic must be in Service Layer
// Controller is just for proper navigation
// We used interface to ensure loose coupling


@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category>categories=new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
    @Override
    public void createCategory(Category category) {
        categories.add(category);
    }
}
