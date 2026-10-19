package com.washeed.tanimart.data;

public class ProductCard {
    private int productID;
    private String productImageID;
    private String productName;
    private float rating;
    private int totalReviews;
    private int discountPercentage;

    private int priceID;
    private int weightKG;
    private int totalPrice;

    public ProductCard(int productID, String productImageID,
                       String productName, float rating,
                       int totalReviews, int discountPercentage,
                       int priceID, int weightKG, int totalPrice) {
        this.productID = productID;
        this.productImageID = productImageID;
        this.productName = productName;
        this.rating = rating;
        this.totalReviews = totalReviews;
        this.discountPercentage = discountPercentage;
        this.priceID = priceID;
        this.weightKG = weightKG;
        this.totalPrice = totalPrice;
    }

    public int getProductID() { return productID; }
    public String getProductImageID() { return productImageID; }
    public String getProductName() { return productName; }
    public float getRating() { return rating; }
    public int getTotalReviews() { return totalReviews; }
    public int getDiscountPercentage() { return discountPercentage; }
    public int getPriceID() { return priceID; }
    public int getWeightKG() { return weightKG; }
    public int getTotalPrice() { return totalPrice; }
}
