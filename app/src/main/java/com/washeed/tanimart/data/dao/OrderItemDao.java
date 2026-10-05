package com.washeed.tanimart.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.entities.OrderItem;

import java.util.List;

@Dao
public interface OrderItemDao {

    @Insert
    long insert(OrderItem orderItem);

    @Update
    void update(OrderItem orderItem);

    @Delete
    void delete(OrderItem orderItem);

    @Query("SELECT * FROM orderItem")
    List<OrderItem> getAllOrderItems();

    @Query("SELECT * FROM orderItem WHERE orderItemID = :orderItemID")
    OrderItem getOrderItemById(int orderItemID);

    @Query("SELECT * FROM orderItem WHERE orderID = :orderID")
    List<OrderItem> getItemsByOrder(int orderID);

    @Query("SELECT * FROM orderItem WHERE productID = :productID")
    List<OrderItem> getItemsByProduct(int productID);

    @Query("DELETE FROM orderItem WHERE orderID = :orderID")
    void deleteItemsByOrder(int orderID);

    @Query("DELETE FROM orderItem")
    void deleteAll();
}
