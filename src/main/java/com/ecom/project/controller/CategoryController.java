package com.ecom.project.controller;
import com.ecom.project.payload.CategoryDTO;
import com.ecom.project.payload.CategoryResponse;
import com.ecom.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
     public ResponseEntity<CategoryResponse>getAllCategories(
             @RequestParam(name = "pageNumber") Integer pageNumber,
             @RequestParam(name = "pageSize") Integer pageSize
     )
    {
        CategoryResponse categoryResponse=categoryService.getAllCategories(pageNumber,pageSize);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
     }
     @PostMapping("/public/categories")
     public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
         CategoryDTO savedcategoryDTO=categoryService.createCategory(categoryDTO);
         //String status= "Category added successfully";
         return new ResponseEntity<>(savedcategoryDTO,HttpStatus.CREATED);
     }
     @DeleteMapping("/admin/categories/{categoryId}")
     public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
            CategoryDTO deleteCategoryDTO=categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deleteCategoryDTO,HttpStatus.OK);
     }
     @PutMapping("/public/categories/{categoryId}")
     public ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId){
            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryId);
            return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);
     }
}
