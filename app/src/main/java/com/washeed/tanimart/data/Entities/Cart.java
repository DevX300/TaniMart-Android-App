package com.washeed.tanimart.data.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart")

public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int cartID;
    private int userID;
    private int productID;
    private int priceID;
    private int quantity;
    private int totalPrice;

    public Cart(int userID, int productID, int priceID, int quantity, int totalPrice) {
        this.userID = userID;
        this.productID = productID;
        this.priceID = priceID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }
}
