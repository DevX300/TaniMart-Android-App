package com.washeed.tanimart.data.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "promoReward")
public class PromoReward {

    @PrimaryKey(autoGenerate = true)
    private int promoID;
    private String promoName;
    private String promoCode;
    private int discount;
    private String conditionDescription;
    private long expiryDate;

    public PromoReward(String promoName, String promoCode, int discount, String conditionDescription, long expiryDate) {
        this.promoName = promoName;
        this.promoCode = promoCode;
        this.discount = discount;
        this.conditionDescription = conditionDescription;
        this.expiryDate = expiryDate;
    }

    public int getPromoID() {
        return promoID;
    }

    public void setPromoID(int promoID) {
        this.promoID = promoID;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }


}
