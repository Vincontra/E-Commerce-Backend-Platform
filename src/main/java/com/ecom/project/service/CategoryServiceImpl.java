package com.ecom.project.service;
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

        categoryRepository.save(category);

    }
    @Override
    public String deleteCategory(Long categoryId) {
        List<Category>categories=categoryRepository.findAll();
        for (int i=0;i<categories.size();i++){
            if (categories.get(i).getCategoryId().equals(categoryId)){
                //categories.remove(i);
                categoryRepository.delete(categories.get(i));
                return "Category with categoryId: " + categoryId + " deleted successfully";
            }
        }
        //return "Category with categoryId: " +categoryId+ " not found";
        // ok so since we dont find that category we should
        // return the messege along with 404NOTFOUND status
        // code as well as we should have control over status codes as well as if we dont even
        // though we dont found we will get 200ok and that is not good
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with categoryId: " +categoryId+ " not found");
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

    @Override
    public void updateCategory(Category category, Long categoryId) {
        List<Category>categories=categoryRepository.findAll();
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getCategoryId().equals(categoryId)){
                category.setCategoryId(categoryId);
                categoryRepository.save(category);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with categoryId: " + categoryId + " not found");
    }
}
