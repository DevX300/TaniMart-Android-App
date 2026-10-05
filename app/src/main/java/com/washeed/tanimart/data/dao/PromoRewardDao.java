package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.entities.PromoReward;

import java.util.List;

@Dao
public interface PromoRewardDao {

    @Insert
    long insert(PromoReward promoReward);

    @Update
    void update(PromoReward promoReward);

    @Delete
    void delete(PromoReward promoReward);

    @Query("SELECT * FROM promoReward")
    List<PromoReward> getAllPromos();

    @Query("SELECT * FROM promoReward WHERE promoID = :promoID")
    PromoReward getPromoById(int promoID);

    @Query("SELECT * FROM promoReward WHERE promoCode = :promoCode")
    PromoReward getPromoByCode(String promoCode);

    @Query("DELETE FROM promoReward")
    void deleteAll();
}