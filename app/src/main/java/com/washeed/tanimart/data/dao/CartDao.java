package com.washeed.tanimart.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.entities.Cart;
import com.washeed.tanimart.data.models.CartItem;

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

    @Query("SELECT * FROM cart WHERE cartID = :cartID AND userID = :userID")
    Cart getCartByIds(int cartID, int userID);

    @Query("SELECT * FROM cart WHERE cartID = :cartID")
    Cart getCartById(int cartID);

    @Query("SELECT * FROM cart WHERE userID = :userID")
    List<Cart> getCartsByUser(int userID);

    @Query("DELETE FROM cart WHERE userID = :userID")
    void deleteUserCart(int userID);

    @Query("DELETE FROM cart")
    void deleteAll();

//    @Query("SELECT c.*, p.productName AS productName, p.productImageID AS productImage, pr.weightKG AS weight FROM Cart c INNER JOIN Product p ON p.productID = c.productID INNER JOIN Price pr ON pr.priceID = c.priceID")
//    public LiveData<List<CartItem>> getCartItems();
}