package com.washeed.tanimart.data.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product  {
    @PrimaryKey(autoGenerate = true)
    private int productID;
    private int categoryID;
    private String productName;
    private String description;
    private String specification;
    private int productImageID;

    public Product(int categoryID, String productName, String description, String specification, int productImageID) {
        this.categoryID = categoryID;
        this.productName = productName;
        this.description = description;
        this.specification = specification;
        this.productImageID = productImageID;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(int productImageID) {
        this.productImageID = productImageID;
    }
}
