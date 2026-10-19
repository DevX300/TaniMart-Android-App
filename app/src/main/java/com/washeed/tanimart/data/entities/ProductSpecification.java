package com.washeed.tanimart.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productSpecification")
public class ProductSpecification {

    @PrimaryKey(autoGenerate = true)
    private int specificationID;
    private int productID;
    private String specificationName;
    private String specificationValue;

    public ProductSpecification(int productID, String specificationName, String specificationValue) {
        this.productID = productID;
        this.specificationName = specificationName;
        this.specificationValue = specificationValue;
    }

    // getters and setters

    public int getSpecificationID() {
        return specificationID;
    }

    public void setSpecificationID(int specificationID) {
        this.specificationID = specificationID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getSpecificationValue() {
        return specificationValue;
    }

    public void setSpecificationValue(String specificationValue) {
        this.specificationValue = specificationValue;
    }
}