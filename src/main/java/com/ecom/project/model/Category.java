package com.ecom.project.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Vinay")
// by default table name would be class name but
// if we want another name just specify like
// @Entity(name="Vinay") table name is Vinay


// Entity means kind of relational table
// so Category is that in this class
// and columns are catid and catname
// we need to mention which one would be the
//primary key else we get error


@Data
@NoArgsConstructor
@AllArgsConstructor

// ok since if we are using lombok annotations we should not write those codes over there

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This tells JPA/Hibernate how to generate the primary key (categoryId) automatically.
    private Long categoryId;

    @NotBlank //validator
    @Size(min = 5,message = "Category name must contain atleast 5 characters")
    private String categoryName;

// this part is commented out as i have used Lombok annotations
//    public Category(Long categoryId, String categoryName) {
//        this.categoryId = categoryId;
//        this.categoryName = categoryName;
//    }
//    public Category(){
//        // acc to jpa it is good practice to have default constructor
//
//    }
//
//
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }

}
