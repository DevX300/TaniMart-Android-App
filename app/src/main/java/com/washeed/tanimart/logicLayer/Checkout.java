package com.washeed.tanimart.logicLayer;

import com.washeed.tanimart.data.entities.Cart;

import java.util.List;

public class Checkout {
    private String transactionID;
    private int totalPrice;
    private List<Cart> carts;
}
