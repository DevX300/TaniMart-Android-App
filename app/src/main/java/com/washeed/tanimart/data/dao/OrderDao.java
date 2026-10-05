package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.entities.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    long insert(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("SELECT * FROM orders")
    List<Order> getAllOrders();

    @Query("SELECT * FROM orders WHERE orderID = :orderID AND userID = :userID")
    Order getOrderByIds(int orderID, int userID);

    @Query("SELECT * FROM orders WHERE orderID = :orderID")
    Order getOrderById(int orderID);

    @Query("SELECT * FROM orders WHERE userID = :userID")
    List<Order> getOrdersByUser(int userID);

    @Query("DELETE FROM orders")
    void deleteAll();
}