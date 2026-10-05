package com.washeed.tanimart.data.entities;


import androidx.room.Entity;

@Entity(tableName = "userPromo", primaryKeys = {"userID", "promoID"})
public class UserPromo {

    private int userID;
    private int promoID;
    private String status;
    private long claimDate;
    private long useDate;

    public UserPromo(int userID, int promoID, String status, long claimDate, long useDate) {
        this.userID = userID;
        this.promoID = promoID;
        this.status = status;
        this.claimDate = claimDate;
        this.useDate = useDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPromoID() {
        return promoID;
    }

    public void setPromoID(int promoID) {
        this.promoID = promoID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(long claimDate) {
        this.claimDate = claimDate;
    }

    public long getUseDate() {
        return useDate;
    }

    public void setUseDate(long useDate) {
        this.useDate = useDate;
    }


}
