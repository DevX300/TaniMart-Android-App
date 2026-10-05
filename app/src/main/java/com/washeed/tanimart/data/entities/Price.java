package com.washeed.tanimart.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "price")
public class Price {
    @PrimaryKey(autoGenerate = true)
    private int priceID;
    private int productID;
    private int weightKG;
    private int stock;
    private int totalPrice;

    public Price(int productID, int weightKG, int stock, int totalPrice) {
        this.productID = productID;
        this.weightKG = weightKG;
        this.stock = stock;
        this.totalPrice = totalPrice;
    }

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getWeightKG() {
        return weightKG;
    }

    public void setWeightKG(int weightKG) {
        this.weightKG = weightKG;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
