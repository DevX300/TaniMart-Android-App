package com.washeed.tanimart.data.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orderItem")
public class OrderItem {

    @PrimaryKey(autoGenerate = true)
    private int orderItemID;
    private int orderID;
    private int productID;
    private int priceID;
    private int quantity;
    private int totalPrice;

    public OrderItem(int orderID, int productID, int priceID, int quantity, int totalPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.priceID = priceID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
