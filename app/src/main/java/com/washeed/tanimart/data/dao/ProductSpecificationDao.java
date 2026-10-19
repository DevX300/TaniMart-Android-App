package com.washeed.tanimart.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.entities.ProductSpecification;

import java.util.List;

@Dao
public interface ProductSpecificationDao {

    @Insert
    long insert(ProductSpecification specification);

    //important to insert multiple specifications of a product at the same time
    @Insert
    List<Long> insertAll(List<ProductSpecification> specifications);

    @Update
    void update(ProductSpecification specification);

    @Delete
    void delete(ProductSpecification specification);

    @Query("DELETE FROM ProductSpecification WHERE specificationID = :specificationId")
    void deleteById(int specificationId);

    @Query("SELECT * FROM ProductSpecification")
    List<ProductSpecification> getAllSpecifications();

    //not much needed
    @Query("SELECT * FROM ProductSpecification WHERE specificationID = :specificationId")
    ProductSpecification getSpecificationById(int specificationId);

    //get all specifications under one product
    @Query("SELECT * FROM ProductSpecification WHERE productID = :productId")
    LiveData<List<ProductSpecification>>getSpecificationsByProductId(int productId);

    //delete all specification under one product
    @Query("DELETE FROM ProductSpecification WHERE productID = :productId")
    void deleteSpecificationsByProductId(int productId);
}