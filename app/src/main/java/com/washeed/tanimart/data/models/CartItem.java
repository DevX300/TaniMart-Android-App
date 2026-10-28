package com.washeed.tanimart.data.models;

import com.washeed.tanimart.data.entities.Cart;
import com.washeed.tanimart.data.entities.Product;
import com.washeed.tanimart.data.entities.Price;

public class CartItem {

    private Cart cart;
    private Product product;
    private Price price;

    public CartItem(Cart cart, Product product, Price price) {
        this.cart = cart;
        this.product = product;
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}