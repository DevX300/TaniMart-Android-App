package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.Entities.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    long insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM product")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product WHERE productID = :productID")
    Product getProductById(int productID);

    @Query("SELECT * FROM product WHERE categoryID = :categoryID")
    List<Product> getProductsByCategory(int categoryID);

    @Query("SELECT * FROM product WHERE productName LIKE '%' || :searchText || '%'")
    List<Product> searchProducts(String searchText);

    @Query("DELETE FROM product")
    void deleteAll();
}