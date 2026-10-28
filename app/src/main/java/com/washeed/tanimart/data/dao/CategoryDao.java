package com.washeed.tanimart.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.CategoryWithCount;
import com.washeed.tanimart.data.entities.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    long insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM category")
    LiveData<List<Category>>getAllCategories();

    @Query("SELECT categoryID FROM category")
    List<Integer> getAllCategoryIds();

    @Query("SELECT * FROM category WHERE categoryID = :categoryID")
    Category getCategoryById(int categoryID);

    @Query("DELETE FROM category")
    void deleteAll();

    @Query("SELECT category.*, COUNT(product.productID) AS productCount FROM category LEFT JOIN product ON category.categoryID = product.categoryID GROUP BY category.categoryID")
    LiveData<List<CategoryWithCount>> getCategoriesWithCount();


    @Query("SELECT category.*, COUNT(product.productID) AS productCount FROM category LEFT JOIN product ON category.categoryID = product.categoryID WHERE category.categoryName LIKE '%' || :searchText || '%' GROUP BY category.categoryID ")
    LiveData<List<CategoryWithCount>> searchCategory(String searchText);

//    @Query("SELECT * FROM category WHERE categoryName LIKE '%' || :searchText || '%'")
//    LiveData<List<Category>> searchCategory(String searchText);


}
