package com.washeed.tanimart.data.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;
    private String categoryName;
    private int categoryIconID;

    public Category(String categoryName, int categoryIconID) {
        this.categoryName = categoryName;
        this.categoryIconID = categoryIconID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryIconID() {
        return categoryIconID;
    }

    public void setCategoryIconID(int categoryIconID) {
        this.categoryIconID = categoryIconID;
    }
}
