package com.washeed.tanimart.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.washeed.tanimart.data.entities.Article;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert
    long insert(Article article);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT articleID FROM article")
    List<Integer> getAllArticleByIds();

    @Query("SELECT * FROM article WHERE articleID = :articleID")
    Article getArticleById(int articleID);

    @Query("DELETE FROM article")
    void deleteAll();
}
