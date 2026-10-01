package com.washeed.tanimart.data.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.Entities.Track;

@Dao
public interface TrackDao {

    @Insert
    long insert(Track track);

    @Update
    void update(Track track);

    @Delete
    void delete(Track track);

    @Query("SELECT * FROM track WHERE trackId = :trackID")
    Track getTrackById(int trackID);

    @Query("SELECT * FROM track WHERE orderID = :orderID")
    Track getTrackByOrder(int orderID);

    @Query("DELETE FROM track")
    void deleteAll();
}