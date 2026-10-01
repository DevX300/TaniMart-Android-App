package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.Entities.UserPromo;

import java.util.List;

@Dao
public interface UserPromoDao {

    @Insert
    void insert(UserPromo userPromo);

    @Update
    void update(UserPromo userPromo);

    @Delete
    void delete(UserPromo userPromo);

    @Query("SELECT * FROM userPromo")
    List<UserPromo> getAllUserPromos();

    @Query("SELECT * FROM userPromo WHERE userID = :userID")
    List<UserPromo> getPromosByUser(int userID);

    @Query("SELECT * FROM userPromo WHERE userID = :userID AND promoID = :promoID")
    UserPromo getUserPromo(int userID, int promoID);

    @Query("DELETE FROM userPromo WHERE userID = :userID")
    void deleteUserPromos(int userID);

    @Query("DELETE FROM userPromo")
    void deleteAll();
}