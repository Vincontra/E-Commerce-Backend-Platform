package com.ecom.project.repositories;

import com.ecom.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    // in generics we have to mention 2 things
    // Entity type and Primary Key Type

    // we dont have to write the implementation code as
    // on runtime
    // spring data jpa will automatically generate the implementation
    // on runtime for this CategoryRepository
    // and we can use it for all the operations we need to do








}
