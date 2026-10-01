package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.Entities.Cart;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    long insert(Cart cart);

    @Update
    void update(Cart cart);

    @Delete
    void delete(Cart cart);

    @Query("SELECT * FROM cart")
    List<Cart> getAllCarts();

    @Query("SELECT * FROM cart WHERE cartID = :cartID")
    Cart getCartById(int cartID);

    @Query("SELECT * FROM cart WHERE userID = :userID")
    List<Cart> getCartsByUser(int userID);

    @Query("DELETE FROM cart WHERE userID = :userID")
    void deleteUserCart(int userID);

    @Query("DELETE FROM cart")
    void deleteAll();
}