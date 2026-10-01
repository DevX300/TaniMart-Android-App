package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.Entities.Price;

import java.util.List;

@Dao
public interface PriceDao {

    @Insert
    long insert(Price price);

    @Update
    void update(Price price);

    @Delete
    void delete(Price price);

    @Query("SELECT * FROM price")
    List<Price> getAllPrices();

    @Query("SELECT * FROM price WHERE priceID = :priceID")
    Price getPriceById(int priceID);

    @Query("SELECT * FROM price WHERE productID = :productID")
    List<Price> getPricesByProduct(int productID);

    @Query("DELETE FROM price")
    void deleteAll();
}