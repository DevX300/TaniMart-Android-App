package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.Entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE userID = :userID")
    User getUserById(int userID);

    @Query("SELECT * FROM user WHERE userEmail = :email LIMIT 1")
    User getUserByEmail(String email);

    @Query("SELECT * FROM user WHERE userEmail = :email AND userPass = :password LIMIT 1")
    User login(String email, String password);

    @Query("DELETE FROM user")
    void deleteAll();
}