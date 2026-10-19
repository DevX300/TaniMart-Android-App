package com.washeed.tanimart.data.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;
    private String categoryName;
    private String categoryIcon;
    private String filters;

    public Category(String categoryName, String categoryIcon, String filters) {
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.filters = filters;
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

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIconID(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}
