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
    private long id=1L;
    // it is not good to have null as id as it may possib;e that user
    // will not enter the id
    // id should be handle by application itself

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
    @Override
    public void createCategory(Category category) {
        category.setCategoryId(id++);
        // even if user sends id it will get overidden here
        // so whether sent or not no problem
        // as we cant expect user to add always a unique id or sometimes he will not
        categories.add(category);
    }
}
