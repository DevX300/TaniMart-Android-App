package com.washeed.tanimart.data.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article")
public class Article {
    @PrimaryKey(autoGenerate = true)
    private int articleID;

    private String articleName;
    private String description;
    private String articleCategory;
    private String articlePublishDate;
    private float articleViews;

    private String articlePictureID;

    public Article(String articleName, String description, String articleCategory, String articlePublishDate, float articleViews, String articlePictureID) {
        this.articleName = articleName;
        this.description = description;
        this.articleCategory = articleCategory;
        this.articlePublishDate = articlePublishDate;
        this.articleViews = articleViews;
        this.articlePictureID = articlePictureID;
    }

    public String getArticlePublishDate() {
        return articlePublishDate;
    }

    public void setArticlePublishDate(String articlePublishDate) {
        this.articlePublishDate = articlePublishDate;
    }

    public float getArticleViews() {
        return articleViews;
    }

    public void setArticleViews(float articleViews) {
        this.articleViews = articleViews;
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

    public String getArticlePictureID() {
        return articlePictureID;
    }

    public void setArticlePictureID(String articlePictureID) {
        this.articlePictureID = articlePictureID;
    }


}
