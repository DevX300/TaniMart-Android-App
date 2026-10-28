package com.washeed.tanimart.logicLayer;

import com.washeed.tanimart.data.entities.Cart;

import java.util.List;

public class Checkout {
    private String productName;
    private String productImageID;
    private int productQuantity;
    private int productBasePrice;

    public Checkout(String productName, String productImageID, int productQuantity, int productBasePrice) {
        this.productName = productName;
        this.productImageID = productImageID;
        this.productQuantity = productQuantity;
        this.productBasePrice = productBasePrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(String productImageID) {
        this.productImageID = productImageID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductBasePrice() {
        return productBasePrice;
    }

    public void setProductBasePrice(int productBasePrice) {
        this.productBasePrice = productBasePrice;
    }
}
