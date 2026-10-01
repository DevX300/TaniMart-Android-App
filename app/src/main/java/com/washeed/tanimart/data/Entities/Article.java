package com.washeed.tanimart.data.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article")
public class Article {
    @PrimaryKey(autoGenerate = true)
    private int articleID;

    private String articleName;
    private String description;
    private String articleCategory;
    private int articlePictureID;

    public Article(String articleName, String description, String articleCategory, int articlePictureID) {
        this.articleName = articleName;
        this.description = description;
        this.articleCategory = articleCategory;
        this.articlePictureID = articlePictureID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public int getArticlePictureID() {
        return articlePictureID;
    }

    public void setArticlePictureID(int articlePictureID) {
        this.articlePictureID = articlePictureID;
    }


}
