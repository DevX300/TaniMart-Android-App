package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.Entities.Category;

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
    List<Category> getAllCategories();

    @Query("SELECT * FROM category WHERE categoryID = :categoryID")
    Category getCategoryById(int categoryID);

    @Query("DELETE FROM category")
    void deleteAll();
}
