package com.washeed.tanimart.data.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product  {
    @PrimaryKey(autoGenerate = true)
    private int productID;
    private int categoryID;
    private String productName;
    private String description;
    private String productImageID;
    private float rating;
    private int totalReviews;
    private int soldCount;
    private int discountPercentage;

    public Product(int categoryID, String productName, String description, String productImageID, float rating, int totalReviews, int soldCount, int discountPercentage) {
        this.categoryID = categoryID;
        this.productName = productName;
        this.description = description;
        this.productImageID = productImageID;
        this.rating = rating;
        this.totalReviews = totalReviews;
        this.soldCount = soldCount;
        this.discountPercentage= discountPercentage;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(String productImageID) {
        this.productImageID = productImageID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
