package com.ecom.project.controller;
import com.ecom.project.model.Category;
import com.ecom.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RequestMapping("/api")
// if we have mappings all over the class and if some part in its endpoints is going to be common
// then that part is also cut down once and we should write it here as written above and then we can
// we have to neglect it on methods level otherwise error in postman
@RestController
public class CategoryController {
    @Autowired // no need to write constructor
    private  CategoryService categoryService;

    // Instead of this constructor we should do autowired
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    //@GetMapping("/api/public/categories")
    // instead of using Mapping with particular names we can use RequestMapping
    // everywhere and it takes two parameters value and method

    @RequestMapping(value ="/public/categories",method=RequestMethod.GET)
     public ResponseEntity<List<Category>>getAllCategories(){
        List<Category>list=categoryService.getAllCategories();
        return new ResponseEntity<>(list,HttpStatus.OK);
     }
     @PostMapping("/public/categories")
     public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
         categoryService.createCategory(category);
         String status= "Category added successfully";
         return new ResponseEntity<>(status,HttpStatus.CREATED);
     }
     @DeleteMapping("/admin/categories/{categoryId}")
     public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
            String status=categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status,HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
     }
     @PutMapping("/public/categories/{categoryId}")
     public ResponseEntity<String>updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
        try {
            categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Category with category id : "+categoryId+" is updated!!",HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
     }

}
