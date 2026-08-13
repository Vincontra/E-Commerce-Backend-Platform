package com.ecom.project.service;
import com.ecom.project.exceptions.APIException;
import com.ecom.project.exceptions.ResourceNotFoundException;
import com.ecom.project.model.Category;
import com.ecom.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// All the bussiness logic must be in Service Layer
// Controller is just for proper navigation
// We used interface to ensure loose coupling

@Service
public class CategoryServiceImpl implements CategoryService {

   // private List<Category>categories=new ArrayList<>(); will use db now since we have set the repo class
    //private long id=1L;  staleobjectstateexception
    // as we had already mentioned  @GeneratedValue(strategy = GenerationType.IDENTITY) in Category class which is Entity
    // it is not good to have null as id as it may possib;e that user
    // will not enter the id
    // id should be handle by application itself

    @Autowired
    private CategoryRepository categoryRepository;

    // like we declare this but we need object of this type
    //otherwise this ref var will be null
    // will give null ptr exception
    // so we should create via constructor or autowired

    @Override
    public List<Category> getAllCategories() {
        //return categories;
        return categoryRepository.findAll();
    }
    @Override
    public void createCategory(Category category) {

        //category.setCategoryId(id++);  // here i have a dount since we have done @GeneratedValue(strategy = GenerationType.IDENTITY) this // should we do this or what
        // doubt resolve since we had declared that in Category class we should not do it here otherwise
        // objectstaleexception somewhat error

        // even if user sends id it will get overidden here
        // so whether sent or not no problem
        // as we cant expect user to add always a unique id or sometimes he will not
        //categories.add(category);

        // what if the category we are going to create may already exist
        // so for that reason i had create APIException class
        // and a global handler for the same
        // first we will check that cat if it exist then Exception
        Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
        // ok so this findByCategoryName method was not there in  categoryRepository class
        // so we create it but we dont need to implement it as
        // jpa will do that implementation internally acc to our need
        // it will implement that sql query
        // but to make this happen we should follow the naming convention  like here method
        // name is findByCategoryName

        if (savedCategory!=null){
            throw new APIException("Category with the name "+category.getCategoryName()+" already exists");
        }
        // otherwise save krlo
        categoryRepository.save(category);

    }

//    @Override
//    public String deleteCategory(Long categoryId) {
//        List<Category>categories=categoryRepository.findAll();
//        for (int i=0;i<categories.size();i++){
//            if (categories.get(i).getCategoryId().equals(categoryId)){
//                //categories.remove(i);
//                categoryRepository.delete(categories.get(i));
//                return "Category with categoryId: " + categoryId + " deleted successfully";
//            }
//        }
//        //return "Category with categoryId: " +categoryId+ " not found";
//        // ok so since we dont find that category we should
//        // return the messege along with 404NOTFOUND status
//        // code as well as we should have control over status codes as well as if we dont even
//        // though we dont found we will get 200ok and that is not good
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId: " +categoryId+ " not found");
//    }


    // more optimised way to write the same

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category>existingCategory=categoryRepository.findById(categoryId);
        if (existingCategory.isPresent()){
            categoryRepository.delete(existingCategory.get());
            return "Category with categoryId: " + categoryId + " deleted successfully";
        }
        //throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId: " +categoryId+ " not found");
        //return "Category with categoryId: " +categoryId+ " not found";
        // ok so since we dont find that category we should
        // return the messege along with 404NOTFOUND status
        // code as well as we should have control over status codes as well as if we dont even
        // though we dont found we will get 200ok and that is not good
        throw new ResourceNotFoundException("Category","categoryId",categoryId);
    }

    // The above one is my logic the below one is of Embark
//    @Override
//    public String deleteCategory(Long categoryId) {
//        Category category = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElse(null);
//
//        if (category==null){
//            return "Category with given id deos not exist!!"
//        }
//        categories.remove(category);
//        return "Category with categoryId: " + categoryId + " deleted successfully";
//    }

//    @Override
//    public void updateCategory(Category category,Long categoryId) {
//        List<Category>categories=categoryRepository.findAll();
//        for (int i=0;i<categories.size();i++){
//            if (categories.get(i).getCategoryId().equals(categoryId)){
//                categories.set(i,category);
//                categories.get(i).setCategoryId(categoryId); // it was updating as null so i fix it here as of now
//                return;
//            }
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId: " +categoryId+ " not found");
//    }

//    @Override
//    public void updateCategory(Category category, Long categoryId) {
//        List<Category>categories=categoryRepository.findAll();
//        for(int i=0;i<categories.size();i++){
//            if(categories.get(i).getCategoryId().equals(categoryId)){
//                category.setCategoryId(categoryId);
//                categoryRepository.save(category);
//                return;
//            }
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with categoryId: " + categoryId + " not found");
//    }
    // more optimised way to write above as we dont need all categories we can directly find out by id

    public void updateCategory(Category category, Long categoryId) {
        Optional<Category>existingCategory=categoryRepository.findById(categoryId);
        if (existingCategory.isPresent()) {
            category.setCategoryId(categoryId);
            categoryRepository.save(category);
            return;
        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId: "+categoryId +" not found");
          throw new ResourceNotFoundException("Category","categoryId",categoryId);
    }
}
